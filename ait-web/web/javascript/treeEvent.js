
/**
 * When a node is expanded, the children nodes are retrived via XHTML Request
 * Then those children are drown in the indicators tree
 * Note: This function is based on YUI Tree example from YUI Library
 */
function loadNodeData(node, fnLoadComplete)  {

    //Getting the label of the selected node
    var nodeId = encodeURI(node.data);

    //Prepare URL for XHR request:
    var sUrl = "/ait-web/ajax/nodeInfo?query=" + nodeId;

    //prepare our callback object
    var callback = {

        //if our XHR call is successful, we want to make use
        //of the returned data and create child nodes.
        success: function(oResponse) {
            //root element -> response
            var root = oResponse.responseXML.documentElement;
            //child node (nodes)
            var rootChildNodes = root.childNodes;
            //
            if(rootChildNodes.length==0){
                node.isLeaf = true;
            }
            //Loop over children
            for(var i=0; i< rootChildNodes.length; i++){
                 var basicElement = root.childNodes[i];  //node
                 var id =  basicElement.childNodes[0].textContent; //id
                 var name =  basicElement.childNodes[1].textContent; //name

                 var tempNode = new YAHOO.widget.TextNode(name, node, false);
                 tempNode.data = id;
            }

            //When we're done creating child nodes, we execute the node's
            //loadComplete callback method which comes in via the argument
            //in the response object (we could also access it at node.loadComplete,
            //if necessary):
            oResponse.argument.fnLoadComplete();

        },

        //if our XHR call is not successful, we want to
        //fire the TreeView callback and let the Tree
        //proceed with its business.
        failure: function(oResponse) {
            YAHOO.log("Failed to process XHR transaction.", "info", "example");
            oResponse.argument.fnLoadComplete();
        },

        //our handlers for the XHR response will need the same
        //argument information we got to loadNodeData, so
        //we'll pass those along:
        argument: {
            "node": node,
            "fnLoadComplete": fnLoadComplete
        },

        //timeout -- if more than 7 seconds go by, we'll abort
        //the transaction and assume there are no children:
        timeout: 7000
    };

    //With our callback object ready, it's now time to
    //make our XHR call using Connection Manager's
    //asyncRequest method:
    YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);
}