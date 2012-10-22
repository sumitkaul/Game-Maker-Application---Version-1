package main.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JFileChooser;

import main.controller.MakerState;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import repos.GameRepo;

public class StateSaver {

    @XStreamOmitField
    private transient Logger log = Logger.getLogger(StateSaver.class);

    public MakerState read() throws FileNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        File currentDirectory = null;
        try {
            currentDirectory = new File(getClass().getClassLoader().getResource("").toURI());
        } catch (URISyntaxException e1) {
            log.error("An exception! Oops!", e1);
        }
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(currentDirectory);
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            FileReader freader = new FileReader(file);
            BufferedReader in = new BufferedReader(freader);
            XStream reader = new XStream(new StaxDriver());
            MakerState makerState = (MakerState) reader.fromXML(in);
            return makerState;
        } else {
            return null;
        }
    }

    public MakerState readGame(String gameName) {
        XStream reader = new XStream(new StaxDriver());
        MakerState makerState = (MakerState) reader.fromXML(this.getClass().getResourceAsStream(GameRepo.getInstance().getGamePath(gameName)));

        return makerState;
    }

    public MakerState readGameFromData(String gameData) {
        XStream reader = new XStream(new StaxDriver());
        MakerState makerState = (MakerState) reader.fromXML(gameData);

        return makerState;
    }

    public String writeGameToString(MakerState state) {
        XStream writer = new XStream(new StaxDriver());
        String xml = writer.toXML(state);
        return xml;
    }

    public void write(MakerState state) {
        XStream writer = new XStream(new StaxDriver());
//		writer.processAnnotations(MakerState.class);
        String xml = writer.toXML(state);
        JFileChooser fileChooser = new JFileChooser();
        File currentDirectory = null;
        try {
            currentDirectory = new File(getClass().getClassLoader().getResource("").toURI());
        } catch (URISyntaxException e1) {
            log.error("An exception! Oops!", e1);
        }
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(currentDirectory);
        fileChooser.showSaveDialog(null);
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            try {
                FileWriter fstream;
                fstream = new FileWriter(file);
                BufferedWriter out = new BufferedWriter(fstream);
                out.write(xml);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
