package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.*;
import Graphs.*;
import api.EdgeData;
import api.NodeData;


public class GraphGUI extends JFrame implements ActionListener {
    GUIPanel panel;
    JMenu saveLoad;
    JMenu edit;
    JMenu algo;
    JMenuItem save;
    JMenuItem load;
    JMenuItem addV;
    JMenuItem removeV;
    JMenuItem addE;
    JMenuItem removeE;
    JMenuItem shortestPath;
    JMenuItem isConnected;
    JMenuItem center;
    JMenuItem TSP;
    JLabel label;
    JFrame subFrame;
    JPanel subPanel;
    DWGraph dirGraph;
    GraphAlgo myGraph;
    double subVar1;
    double subVar2;
    boolean readyToEdit;
    JTextField xFromUser;
    JTextField yFromUser;
    JTextField zFromUser;
    JLabel sublabel;
    JButton loadButton;
    JButton saveButton;
    JButton addVbutton;
    JButton removeVbutton;
    JButton addEbutton;
    JButton removeEbutton;
    JButton isconnectedButton;
    JButton shortestPathButton;
    JButton tspButton;
    Node centerNode;
    int width =1000;
    int height =700;

    java.util.List<NodeData> shortest;
    java.util.List<NodeData> tspPath;

    public class GUIPanel extends JPanel {

        LinkedList<Node> nodeList = new LinkedList<>();
        LinkedList<Edge> edgeList = new LinkedList<>();
        java.util.List<NodeData> shortest = new ArrayList<>();
        LinkedList<Edge> shortestList = new LinkedList<>();
        Iterator<NodeData> nodeIt;
        Iterator<EdgeData> edgeIt;
        Node centerNode;

        public GUIPanel() {
            super();
            this.nodeIt = dirGraph.nodeIter();
            this.edgeIt = dirGraph.edgeIter();
            while (nodeIt.hasNext()){
                this.nodeList.add((Node)nodeIt.next());
            }
            while (edgeIt.hasNext()){
                this.edgeList.add((Edge)edgeIt.next());
            }

        }

        public void setNew(){
            this.nodeIt = dirGraph.nodeIter();
            this.edgeIt =dirGraph.edgeIter();
            this.nodeList = new LinkedList<>();
            this.edgeList = new LinkedList<>();
            this.centerNode = null;
            while (nodeIt.hasNext()){
                this.nodeList.add((Node) nodeIt.next());
            }
            while (edgeIt.hasNext()){
                this.edgeList.add((Edge)edgeIt.next());
            }
            while (!this.shortest.isEmpty()){
                this.shortest.remove(0);
            }

        }

        private void initShortest(ArrayList<NodeData> l){
            this.centerNode = null;
            this.shortest = new ArrayList<>(l.size());
            while (!l.isEmpty()){
                this.shortest.add(l.get(0));
                l.remove(0);
            }
        }

        private void initCenter(Node n){
            this.centerNode = n;
            while (!this.shortest.isEmpty()){
                this.shortest.remove(0);
            }
        }

        private int arrLoc(int key){
            for(int i=0; i<this.nodeList.size();i++){
                if(this.nodeList.get(i).getKey() == key)
                    return i;
            }
            return -1;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            boolean sameNode;
            int index=0;
            for (int i=0; i< this.nodeList.size();i++){
                Node temp = this.nodeList.get(i);
                int xPixel = (int) scaleX(temp.getLocation().x());
                int yPixel = (int) scaleY(temp.getLocation().y());
                if(temp == this.centerNode){
                    g.setColor(new Color(255,215,0));
                    g.fillOval(xPixel,yPixel,22,22);
                    g.setColor(Color.BLACK);
                    g.drawString("Key:" + temp.getKey(), xPixel - 10, yPixel + 35);
                }
                else {
                    g.setColor(Color.BLUE);
                    g.fillOval(xPixel, yPixel, 22, 22);
                    g.setColor(Color.BLACK);
                    g.drawString("Key:" + temp.getKey(), xPixel - 10, yPixel + 35);
                }
                sameNode =true;
                while (sameNode && index<this.edgeList.size()){
                    Edge tempEdge = this.edgeList.get(index);
                    if(tempEdge.getSrc() != this.nodeList.get(i).getKey()){
                        sameNode =false;
                        continue;
                    }
                    else {
                        index++;
                        int arrLocation = arrLoc(tempEdge.getDest());
                            double xDst = this.nodeList.get(arrLocation).getLocation().x();
                            double yDst = this.nodeList.get(arrLocation).getLocation().y();
                            int xPixelDst = (int) scaleX(xDst);
                            int yPixelDst = (int) scaleY(yDst);
                            int xMiddle = (xPixel+xPixelDst)/2;
                            int yMiddle = (yPixel+yPixelDst)/2;
                            double weight = tempEdge.getWeight();
                            String weightStr = String.format("%.3f",weight);
                            if(this.shortest.contains(temp)){
                                int ind = getIndex(temp);
                                if(ind+1 < this.shortest.size() && this.shortest.get(ind+1).getKey() == tempEdge.getDest()){
                                    g.setColor(Color.GREEN);
                                    g.drawLine(xPixel + 12, yPixel + 12, xPixelDst + 12, yPixelDst + 12);
                                    g.drawString(weightStr,xMiddle,yMiddle);
                                }
                            }
                            else{
                            g.setColor(Color.RED);
                            g.drawLine(xPixel + 12, yPixel + 12, xPixelDst + 12, yPixelDst + 12);
                            g.drawString(weightStr,xMiddle,yMiddle);
                    }
                    }
                }
            }
            for (int i=0; i< this.shortest.size();i++){
                Node temp = (Node) this.shortest.get(i);
                int xPixel = (int) scaleX(temp.getLocation().x());
                int yPixel = (int) scaleY(temp.getLocation().y());
                g.setColor(Color.GREEN);
                g.fillOval(xPixel,yPixel,22,22);
                //g.setColor(Color.BLACK);
                //g.drawString("Key:"+temp.getKey(),xPixel -10,yPixel+35);
            }
            dirGraph.setFlag();
        }
        private int getIndex(Node n){
            for (int i =0; i<this.shortest.size();i++){
                if(this.shortest.get(i) == n){
                    return i;
                }
            }
            return -1;
        }
    }

    public GraphGUI(String jsonFile){
        super();
        JFrame frame = new JFrame();
        this.subFrame = new JFrame();
        this.subPanel = new JPanel();
        this.subFrame.setSize(200,100);
        String g1 = jsonFile;
        this.dirGraph = new DWGraph(g1);
        this.myGraph = new GraphAlgo(this.dirGraph);
        panel = new GUIPanel();
        panel.setLayout(null);
        this.add(panel);
        this.subPanel.setSize(400,200);
        this.subFrame.setSize(400,200);
        this.subPanel.setLayout(null);

        this.readyToEdit = false;

        JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);

        saveLoad = new JMenu("Sava/Load");
        menubar.add(saveLoad);
        save= new JMenuItem("Save to JSON");
        load = new JMenuItem("Load from JSON");
        saveLoad.add(save);
        saveLoad.add(load);
        save.addActionListener(this);
        load.addActionListener(this);

        edit = new JMenu("Edit graph");
        menubar.add(edit);
        addV = new JMenuItem("Add vertex");
        removeV = new JMenuItem("Remove vertex");
        addE = new JMenuItem("Add edge");
        removeE = new JMenuItem("Remove edge");
        edit.add(addV);
        edit.add(removeV);
        edit.add(addE);
        edit.add(removeE);
        addV.addActionListener(this);
        addE.addActionListener(this);
        removeV.addActionListener(this);
        removeE.addActionListener(this);


        xFromUser = new JTextField();

        yFromUser = new JTextField();

        zFromUser = new JTextField();

        sublabel = new JLabel("Enter Location:");
        sublabel.setSize(400,20);
        sublabel.setBounds(145,30,400,20);

        saveButton = new JButton();
        saveButton.setText("Save");
        saveButton.setSize(65,30);
        saveButton.setBounds(165,110,65,30);
        saveButton.addActionListener(this);

        loadButton = new JButton();
        loadButton.setText("Load");
        loadButton.setSize(65,30);
        loadButton.setBounds(165,110,65,30);
        loadButton.addActionListener(this);

        addVbutton = new JButton();
        addVbutton.setSize(65,30);
        addVbutton.setBounds(165,110,65,30);
        addVbutton.addActionListener(this);

        removeVbutton = new JButton();
        removeVbutton.setSize(65,30);
        removeVbutton.setBounds(165,110,65,30);
        removeVbutton.addActionListener(this);

        addEbutton = new JButton();
        addEbutton.setSize(65,30);
        addEbutton.setBounds(165,110,65,30);
        addEbutton.addActionListener(this);

        removeEbutton = new JButton();
        removeEbutton.setSize(65,30);
        removeEbutton.setBounds(165,110,65,30);
        removeEbutton.addActionListener(this);

        isconnectedButton = new JButton();
        isconnectedButton.setText("Done");
        isconnectedButton.setSize(65,30);
        isconnectedButton.setBounds(165,110,65,30);
        isconnectedButton.addActionListener(this);

        shortestPathButton = new JButton();
        shortestPathButton.setText("Find!");
        shortestPathButton.setSize(65,30);
        shortestPathButton.setBounds(165,110,65,30);
        shortestPathButton.addActionListener(this);

        tspButton = new JButton();
        tspButton.setText("Find!");
        tspButton.setSize(65,30);
        tspButton.setBounds(165,110,65,30);
        tspButton.addActionListener(this);

        algo = new JMenu("Algorithm");
        menubar.add(algo);
        shortestPath = new JMenuItem("Shorted Path");
        isConnected = new JMenuItem("Is connected?");
        center = new JMenuItem("Center");
        TSP = new JMenuItem("TSP");
        algo.add(shortestPath);
        algo.add(isConnected);
        algo.add(center);
        algo.add(TSP);

        shortestPath.addActionListener(this);
        isConnected.addActionListener(this);
        center.addActionListener(this);
        TSP.addActionListener(this);

        label = new JLabel("Use the menu bar to choose function");
        label.setBounds(30,400,width-30,height-270);
        panel.add(label);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ex2: Graph");
        this.setSize(width,height);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == save){
            this.subPanel.removeAll();
            xFromUser.setText("");
            this.sublabel.setBounds(120,30,400,30);
            xFromUser.setSize(400,30);
            xFromUser.setBounds(20,60,350,20);
            this.sublabel.setText("Enter full file location");
            this.subPanel.add(saveButton);
            this.subPanel.add(sublabel);
            this.subPanel.add(xFromUser);
            this.subFrame.add(this.subPanel);
            this.subFrame.setVisible(true);
        }
        else if (e.getSource() == load){
            this.subPanel.removeAll();
            xFromUser.setText("");
            this.sublabel.setBounds(120,30,400,30);
            xFromUser.setSize(400,30);
            xFromUser.setBounds(20,60,350,20);
            this.sublabel.setText("Enter full file location");
            this.subPanel.add(loadButton);
            this.subPanel.add(sublabel);
            this.subPanel.add(xFromUser);
            this.subFrame.add(this.subPanel);
            this.subFrame.setVisible(true);
        }



        else if(e.getSource() == addV) {
            if (this.dirGraph != null) {
                this.subPanel.removeAll();
                xFromUser.setText("");
                yFromUser.setText("");
                addVbutton.setText("Enter location");
                xFromUser.setSize(60,30);
                xFromUser.setBounds(130,60,60,20);
                yFromUser.setSize(60,30);
                yFromUser.setBounds(190,60,60,20);
                this.myGraph = new GraphAlgo(this.dirGraph);
                this.subPanel.add(addVbutton);
                this.subPanel.add(sublabel);
                this.subPanel.add(xFromUser);
                this.subPanel.add(yFromUser);
                this.subFrame.add(this.subPanel);
                this.subFrame.setVisible(true);
            }
        }
        else if(e.getSource() == removeV){
            System.out.println("removeV was clicked");
            if(this.dirGraph !=null){
                //get from user key to delete
                this.subPanel.remove(yFromUser);
                xFromUser.setText("");
                this.sublabel.setText("Enter vertex Key to remove:");
                this.sublabel.setBounds(120,30,400,20);
                this.xFromUser.setBounds(155,70,80,20);
                this.myGraph = new GraphAlgo(this.dirGraph);
                this.removeVbutton.setText("Remove!");
                this.subPanel.removeAll();
                this.subPanel.add(removeVbutton);
                this.subPanel.add(sublabel);
                this.subPanel.add(xFromUser);
                this.subFrame.add(this.subPanel);
                this.subFrame.setVisible(true);
                label.setText("Vertex was successfully removed");
            }
        }
        else if(e.getSource() == addE){
            if(this.dirGraph!= null){
                this.subPanel.removeAll();
                xFromUser.setText("");
                yFromUser.setText("");
                zFromUser.setText("");
                addEbutton.setText("Add!");
                this.sublabel.setText("Enter SRC,DST,and WEIGHT for new edge");
                this.sublabel.setBounds(90,30,400,20);
                this.xFromUser.setSize(60,30);
                this.xFromUser.setBounds(95,60,60,20);
                this.yFromUser.setSize(60,30);
                this.yFromUser.setBounds(155,60,60,20);
                this.zFromUser.setSize(60,30);
                this.zFromUser.setBounds(215,60,60,20);
                this.myGraph = new GraphAlgo(this.dirGraph);
                this.subPanel.add(addEbutton);
                this.subPanel.add(sublabel);
                this.subPanel.add(xFromUser);
                this.subPanel.add(yFromUser);
                this.subPanel.add(zFromUser);
                this.subFrame.add(this.subPanel);
                this.subFrame.setVisible(true);
            }
        }
        else if(e.getSource() == removeE){
            System.out.println("removeE was clicked");
            if(this.dirGraph!=null){
                this.subPanel.removeAll();
                xFromUser.setText("");
                yFromUser.setText("");
                this.sublabel.setText("Enter Edge SRC and DST to remove:");
                this.sublabel.setBounds(100,30,400,20);
                xFromUser.setSize(60,30);
                xFromUser.setBounds(130,60,60,20);
                yFromUser.setSize(60,30);
                yFromUser.setBounds(190,60,60,20);
                this.myGraph = new GraphAlgo(this.dirGraph);
                this.removeEbutton.setText("Remove!");
                this.subPanel.add(removeEbutton);
                this.subPanel.add(sublabel);
                this.subPanel.add(xFromUser);
                this.subPanel.add(yFromUser);
                this.subFrame.add(this.subPanel);
                this.subFrame.setVisible(true);
                label.setText("Edge removed successfully");
            }
        }

        else if(e.getSource() == shortestPath){
            this.subPanel.removeAll();
            xFromUser.setText("");
            yFromUser.setText("");
            this.sublabel.setText("Enter SRC and DST to find shortest path:");
            this.sublabel.setBounds(100,30,400,20);
            xFromUser.setSize(60,30);
            xFromUser.setBounds(130,60,60,20);
            yFromUser.setSize(60,30);
            yFromUser.setBounds(190,60,60,20);
            this.myGraph = new GraphAlgo(this.dirGraph);
            this.subPanel.add(shortestPathButton);
            this.subPanel.add(sublabel);
            this.subPanel.add(xFromUser);
            this.subPanel.add(yFromUser);
            this.subFrame.add(this.subPanel);
            this.subFrame.setVisible(true);
            System.out.println("shortestPath was clicked");

        }
        else if(e.getSource() == isConnected){
            subPanel.removeAll();
            subPanel.add(this.isconnectedButton);
            sublabel.setBounds(110,40,200,30);
            if(this.myGraph.isConnected()){
                sublabel.setText("The graph is connected!");
            }
            else {
                sublabel.setText("The graph is NOT connected");
            }
            this.dirGraph.setFlag();
            subPanel.add(this.sublabel);
            subFrame.add(this.subPanel);
            subFrame.setVisible(true);
        }
        else if(e.getSource() == center){
            this.centerNode = (Node)this.myGraph.center();
            this.label.setText("The center node is: " + this.centerNode.getKey());
            panel.initCenter(this.centerNode);
            this.repaint();
        }
        else if(e.getSource() == TSP){
            this.subPanel.removeAll();
            xFromUser.setText("");
            this.sublabel.setBounds(60,30,400,30);
            xFromUser.setSize(400,30);
            xFromUser.setBounds(20,60,350,20);
            this.sublabel.setText("Enter vertex you want to visit divided by , (Comma) !!");
            this.subPanel.add(tspButton);
            this.subPanel.add(sublabel);
            this.subPanel.add(xFromUser);
            this.subFrame.add(this.subPanel);
            this.subFrame.setVisible(true);
            System.out.println("TSP was clicked");
        }
        else if (e.getSource() == addVbutton){
            subVar1 = Integer.parseInt(xFromUser.getText());
            subVar2 = Integer.parseInt(yFromUser.getText());
            GeoLocationData temp = new GeoLocationData(subVar1, subVar2, 1);
            Node tempNode = new Node(this.dirGraph.nextKey(), temp);
            this.dirGraph.addNode(tempNode);
            this.subFrame.dispose();
            panel.setNew();
            panel.repaint();
            label.setText("Vertex was added successfully");
        }

        else if (e.getSource() == removeVbutton) {
            subVar1 = Integer.parseInt(xFromUser.getText());
            Node removed = (Node) this.dirGraph.removeNode((int)subVar1);
            if (removed == null) {
                label.setText("No such key");
            } else {
                this.dirGraph.edgeSize();
                this.subFrame.dispose();
                panel.setNew();
                panel.repaint();
                label.setText("Vertex was removed");
            }
        }

        else if (e.getSource() == addEbutton) {
            this.subFrame.dispose();
            subVar1 = Integer.parseInt(xFromUser.getText());
            subVar2 = Integer.parseInt(yFromUser.getText());
            int weight = Integer.parseInt(zFromUser.getText());
            if (this.myGraph.getGraph().getNode((int) subVar1) == null || this.myGraph.getGraph().getNode((int) subVar2) == null) {
                this.label.setText("Cant connect edge to no existed vertex!");
            } else {
                this.dirGraph.connect((int) subVar1, (int) subVar2, weight);
                this.dirGraph.edgeSize();
                panel.setNew();
                panel.repaint();
                label.setText("Edge was added");
            }
        }

        else if(e.getSource() == removeEbutton){
            subVar1 = Integer.parseInt(xFromUser.getText());
            subVar2 = Integer.parseInt(yFromUser.getText());
            Edge removed = (Edge) this.dirGraph.removeEdge((int) subVar1,(int) subVar2);
            if(removed == null){
                this.label.setText("No such edge!");
                this.subFrame.dispose();
            }
            else {
                this.subFrame.dispose();
                panel.setNew();
                panel.repaint();
                this.label.setText("Edged removed!");
            }
        }
        else if(e.getSource() == loadButton){
            this.subFrame.dispose();
            String g1 = xFromUser.getText();
            this.dirGraph = new DWGraph(g1);
            this.myGraph = new GraphAlgo(this.dirGraph);
            panel.setNew();
            this.repaint();
        }

        else if (e.getSource() == saveButton){
            this.subFrame.dispose();
            String g1 = xFromUser.getText();
            this.myGraph.save(g1);
        }
        else if(e.getSource() == isconnectedButton){
            this.subFrame.dispose();
        }

        else if(e.getSource() == shortestPathButton) {
            this.subFrame.dispose();
            subVar1 = Integer.parseInt(xFromUser.getText());
            subVar2 = Integer.parseInt(yFromUser.getText());
            if (this.myGraph.getGraph().getNode((int) subVar1) == null || this.myGraph.getGraph().getNode((int) subVar2) == null) {
                this.label.setText("Cant find shortest path to no existed vertex!");
            } else {
                this.shortest = this.myGraph.shortestPath((int) subVar1, (int) subVar2);
                double distance = this.myGraph.shortestPathDist((int) subVar1, (int) subVar2);
                panel.initShortest((ArrayList<NodeData>) this.shortest);
                this.repaint();
                this.label.setText("The shortest path between vertex " + subVar1 + " and vertex " + subVar2 + " is: " + distance);
            }
        }

        else if (e.getSource() == tspButton) {
            Double cost = 0.0;
            boolean out = false;
            this.subFrame.dispose();
            java.util.List<NodeData> citiesNodes = new ArrayList<>();
            LinkedList<Integer> temp = new LinkedList<>();
            String[] myCities = xFromUser.getText().split(",");
            for (int i = 0; i < myCities.length; i++) {
                temp.add(Integer.parseInt(myCities[i]));
            }
            for (int i = 0; i < temp.size(); i++) {
                if (this.myGraph.getGraph().getNode(temp.get(i)) == null) {
                    out = true;
                }
            }
            Iterator<NodeData> nodeIt = this.myGraph.getGraph().nodeIter();
            int i = 1, src, dst;
            double tempweight;
            if (out) {
                this.label.setText("Cant travel to no existed vertex!");
            } else {
                while (nodeIt.hasNext()) {
                    tempweight = 0;
                    Node tempNode = (Node) nodeIt.next();
                    if (temp.contains(tempNode.getKey())) {
                        citiesNodes.add(tempNode);
                        if (i < myCities.length) {
                            src = tempNode.getKey();
                            dst = Integer.parseInt(myCities[i]);
                            Edge tempEdge = (Edge) this.myGraph.getGraph().getEdge(src, dst);
                            tempweight = tempEdge.getWeight();
                            i++;
                        }
                        cost = cost + tempweight;
                    }
                }
                this.tspPath = this.myGraph.tsp(citiesNodes);
                String result = "Result of TSP : " + String.valueOf(this.tspPath.get(0).getKey());
                Iterator<NodeData> pathIt = this.tspPath.iterator();
                pathIt.next();
                while (pathIt.hasNext()) {
                    result = result + " --> " + String.valueOf(pathIt.next().getKey());
                }
                result = result + " The total cost is: " + String.valueOf(cost);
                this.panel.setNew();
                this.panel.repaint();
                this.label.setText(result);
            }
        }
    }

    private double scaleX (double x){
        double[] bounds = this.dirGraph.getBounds();
        return ((this.width -100) * (x - bounds[0])/(bounds[1]-bounds[0]));
    }

    private  double scaleY (double y){
        double[] bounds = this.dirGraph.getBounds();
        return ((this.height -130) * (bounds[3] - y) / (bounds[3] -bounds[2]));
    }
}
