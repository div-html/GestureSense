import javax.swing.*;

public class GameMatchmaking {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Game Matchmaking System");
        frame.setSize(600, 500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel nameLabel = new JLabel("Player Name:");
        nameLabel.setBounds(30, 40, 100, 30);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(140, 40, 200, 30);
        frame.add(nameField);

        JLabel gameLabel = new JLabel("Game:");
        gameLabel.setBounds(30, 90, 100, 30);
        frame.add(gameLabel);

        String games[] = {"Valorant", "CS2", "FIFA", "Apex Legends"};

        JComboBox<String> gameBox = new JComboBox<>(games);
        gameBox.setBounds(140, 90, 200, 30);
        frame.add(gameBox);

        JLabel modeLabel = new JLabel("Mode:");
        modeLabel.setBounds(30, 140, 100, 30);
        frame.add(modeLabel);

        JRadioButton ranked = new JRadioButton("Ranked");
        ranked.setBounds(140, 140, 80, 30);

        JRadioButton casual = new JRadioButton("Casual");
        casual.setBounds(220, 140, 80, 30);

        ButtonGroup group = new ButtonGroup();
        group.add(ranked);
        group.add(casual);

        frame.add(ranked);
        frame.add(casual);

        JCheckBox voiceChat = new JCheckBox("Voice Chat");
        voiceChat.setBounds(140, 180, 120, 30);
        frame.add(voiceChat);

        JCheckBox crossplay = new JCheckBox("Crossplay");
        crossplay.setBounds(260, 180, 100, 30);
        frame.add(crossplay);

        JLabel mapLabel = new JLabel("Map:");
        mapLabel.setBounds(30, 230, 100, 30);
        frame.add(mapLabel);

        String maps[] = {"Ascent", "Bind", "Haven", "Lotus"};

        JList<String> mapList = new JList<>(maps);

        JScrollPane listScroll = new JScrollPane(mapList);
        listScroll.setBounds(140, 220, 120, 80);
        frame.add(listScroll);
        JToggleButton online = new JToggleButton("Offline");
        online.setBounds(300, 220, 100, 30);
        frame.add(online);
        JButton findButton = new JButton("Find Match");
        findButton.setBounds(140, 330, 120, 35);
        frame.add(findButton);
        String columns[] = {"Player", "Rank", "Ping"};

        String data[][] = {
            {"Alex", "Diamond", "30ms"},
            {"Rahul", "Platinum", "45ms"},
            {"Sam", "Gold", "50ms"}
        };

        JTable table = new JTable(data, columns);

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBounds(30, 380, 400, 80);
        frame.add(tableScroll);
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu matchMenu = new JMenu("Match");

        JMenuItem newMatch = new JMenuItem("New Match");
        JMenuItem exit = new JMenuItem("Exit");

        fileMenu.add(newMatch);
        fileMenu.add(exit);

        menuBar.add(fileMenu);
        menuBar.add(matchMenu);

        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }
}