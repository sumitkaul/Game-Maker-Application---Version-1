package repos;

import com.google.gson.Gson;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class GameSavePanel {

    private final static Logger log = Logger.getLogger(GameSavePanel.class);
    private JPanel rootPanel;
    private final String host = "99.127.253.242:8080";
    private final String path = "/GameMakerServer";
    private final String urlSaveGame = "/saveGame";

    public GameSavePanel(JPanel rootPanel) {
        this.rootPanel = rootPanel;
    }

    public void saveGameToRemoteServer(String gameData) {
        try {
            String gameName = JOptionPane.showInputDialog(rootPanel, "Please give your game a name:", "Save Game", JOptionPane.PLAIN_MESSAGE);
            if (gameName == null) {
                JOptionPane.showMessageDialog(rootPanel, "Please type something");
                return;
            }
            if (gameName.isEmpty()) {
                JOptionPane.showMessageDialog(rootPanel, "Please type something");
                return;
            }

            URIBuilder ub = new URIBuilder();
            ub.setScheme("http").setHost(host).setPath(path + urlSaveGame);
            URI uri = ub.build();

            List<NameValuePair> nvps = new ArrayList<NameValuePair>(2);
            nvps.add(new BasicNameValuePair("game_name", gameName));
            nvps.add(new BasicNameValuePair("game_data", gameData));

            String json = httpPost(uri, nvps);

            Gson gson = new Gson();
            Boolean saveOK = gson.fromJson(json, Boolean.class);

            if (saveOK) {
                JOptionPane.showMessageDialog(rootPanel, "Game saved to " + host + " successfully");
            } else {
                JOptionPane.showMessageDialog(rootPanel, "Error, Game is not saved");
            }

        } catch (Exception ex) {
            log.error(ex);
            JOptionPane.showMessageDialog(rootPanel, ex.toString());
        }
    }

    public String httpPost(URI uri, List<NameValuePair> params) throws Exception {
        log.debug("requesting: " + uri);

        DefaultHttpClient httpclient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost(uri);
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        HttpResponse response = httpclient.execute(httpPost);

        StringBuilder sb = new StringBuilder(100);
        try {
            HttpEntity entity = response.getEntity();
            Scanner scan = new Scanner(entity.getContent());

            while (scan.hasNextLine()) {
                sb.append(scan.nextLine());
            }

            log.debug("receive: " + sb.toString());

            EntityUtils.consume(entity);
        } finally {
            httpPost.releaseConnection();
        }
        return sb.toString();
    }
}
