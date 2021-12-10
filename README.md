# Ex2-OOP - Graphs

## Made by:
* Matanel Ohayon - https://github.com/Matanel1995 <br />
* Roey Finegold -  https://github.com/RF555 <br />
## Introduction:          
This project is about ֿimplementing and presenting graphs.<br />
This project splits into 2 parts.<br />
### Part one: <br />
implementation of directed graphs and algorithms on those graphs <br />
for example : Shortest path between 2 vertices, find center of the graph and more.<br />
### Part two:<br />
implementation of GUI <br />
in the GUI the user will be able to see the graph and change it or run function on the graph<br />
for example the user can load graphs from JSON files save graphs to JSON files<br />
and run all the functions that was implemented in part one.<br />

## GUI:
All the functions and edits can be done by the menu bar <br />
At each function or edit new window will popped up asking the user for the necessary details <br />
At the bottom of the screen there is label with all the information about the results.<br /> <br /> <br />
This is how the GUI look like:
![Graph](https://user-images.githubusercontent.com/92520981/145411833-9b74c430-47cd-4548-92a4-7f701afefc62.PNG)
<br />
<br />
for example let assume the user chose to find the shortest path between 2 vertices, new window will pop up and ask for the 2 vertices
<br />
<br />
![popUp exemple](https://user-images.githubusercontent.com/92520981/145412374-708de5af-21d6-498e-ab92-ec6f2c6ff710.PNG)

## Supported functions<br />
### Directed graph:<br />
| Function name | Explanation |
| ------------- | ------------- |
| getNode(int key)| returns the node_data by the node_id  |
| EdgeData getEdge(int src, int dest)  | eturns the data of the edge (src,dest), null if none  |
| void addNode(NodeData n) | adds a new node to the graph with the given node_data |
| void connect(int src, int dest, double w) | Connects an edge with weight w between node src to node dest |
| Iterator<NodeData> nodeIter() | returns an Iterator for all the nodes in this graph |
| Iterator<EdgeData> edgeIter() | returns an Iterator for all the edges in this graph |
| Iterator<EdgeData> edgeIter(int node_id) | returns an Iterator for edges getting out of the given node|
| NodeData removeNode(int key) | Deletes the node (with the given ID) from the graph|
| EdgeData removeEdge(int src, int dest) | Deletes the edge from the graph |
| int nodeSize() | Returns the number of vertices (nodes) in the graph | 
| int edgeSize() | Returns the number of edges|
### Directed graph algorithms:<br />

| Function name | Explanation |
| ------------- | ------------- |
| void init(DirectedWeightedGraph g)  | Inits the graph on which this set of algorithms operates on  |
| DirectedWeightedGraph getGraph()  | Returns the underlying graph of which this class works  |
| DirectedWeightedGraph copy() | Computes a deep copy of this weighted graph |
| boolean isConnected() | Returns true if and only if (iff) there is a valid path from each node to each node |
| double shortestPathDist(int src, int dest) | Computes the length of the shortest path between src to dest |
| List<NodeData> shortestPath(int src, int dest) | Computes the the shortest path between src to dest - as an ordered List of nodes |
| NodeData center() | Finds the NodeData which minimizes the max distance to all the other nodes |
| List<NodeData> tsp(List<NodeData> cities) | Computes a list of consecutive nodes which go over all the nodes in cities|
| boolean save(String file) | Saves this weighted (directed) graph to the given |
| boolean load(String file) |  loads a graph to this graph algorithm |
<br />

## Algorithm time:
![times](https://user-images.githubusercontent.com/92520981/145440500-98698d7d-7218-4550-bfc3-b38a1e5c9035.PNG)
<br /> 

## How to run the program:
Download the project <br /> 
Open the CMD in the this directory <br />
Run this function:
<br />
>java -jar Ex2.jar filePath
<br />
FilePath should be the full path to JSON file.
<br />

![how to run](https://user-images.githubusercontent.com/92520981/145442632-5a1a2692-2e16-4d19-9fc8-b95d68152de5.PNG)

