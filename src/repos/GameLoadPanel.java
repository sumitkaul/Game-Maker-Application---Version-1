package repos;

import com.google.gson.Gson;
import java.net.URI;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class GameLoadPanel {

    private final static Logger log = Logger.getLogger(GameLoadPanel.class);
    private JPanel rootPanel;
    private final String host = "99.127.253.242:8080";
    private final String path = "/GameMakerServer";
    private final String urlLoadAllGames = "/loadAllGames";
    private final String urlLoadGame = "/loadGame";

    public GameLoadPanel(JPanel rootPanel) {
        this.rootPanel = rootPanel;
    }

    public String readGameDataFromRemoteList() {
        Set<String> gameNames = null;
        try {
            URIBuilder ub = new URIBuilder();
            ub.setScheme("http").setHost(host).setPath(path + urlLoadAllGames);
            URI uri = ub.build();

            String jsonGameList = httpGet(uri);

            Gson gson = new Gson();

            gameNames = gson.fromJson(jsonGameList, Set.class);

        } catch (Exception ex) {
            log.error(ex);
            JOptionPane.showMessageDialog(rootPanel, ex.toString());
            return null;
        }

        Object[] possibilities = gameNames.toArray();
        String chosen = (String) JOptionPane.showInputDialog(
                rootPanel,
                "Select Game from " + host,
                "Load Game",
                JOptionPane.PLAIN_MESSAGE,
                null, possibilities,
                null);

        if (chosen == null) {
            return null;
        }

        return readGame(chosen);
    }

    public String readGame(String gameName) {
        try {
            URIBuilder ub = new URIBuilder();
            ub.setScheme("http").setHost(host).setPath(path + urlLoadGame).setParameter("game_name", gameName);
            URI uri = ub.build();

            String gameDataXml = httpGet(uri);

            return gameDataXml;

        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }

    public String httpGet(URI uri) throws Exception {
        log.debug("requesting: " + uri);

        DefaultHttpClient httpclient = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(uri);
        HttpResponse response = httpclient.execute(httpGet);

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
            httpGet.releaseConnection();
        }
        return sb.toString();
    }
}
