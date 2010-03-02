
/**
 * Execute final query by especific parameters
 */
function executeFinalQuery(selectedLayers,selectedTaxa,selectedIndicators)  {

    //Prepare URL for XHR request:
    var sUrl = "/ait-web/ajax/finalQuery?layers="+selectedLayers+"&taxons="+selectedTaxa+"&indi="+selectedIndicators;

    //Prepare callback object
    var callback2 = {

        //If XHR call is successful
        success: function(oResponse) {
            //root element -> response
            var response = oResponse.responseXML.documentElement;
            //child node (nodes)
            var totalCountNode = response.childNodes[0];
            //Get total count data
            var totalCount = totalCountNode.textContent;
            //Limpiar las listas de criterios
            document.getElementById('mapParameters').innerHTML = "";
            document.getElementById('taxParameters').innerHTML = "";
            document.getElementById('treeParameters').innerHTML = "";
            tree.collapseAll();            
            //Mostrar el resultado y los criterios de la búsqueda
            var resultHTML = "<b>Criterios de búsqueda:</b><br>";
            resultHTML += "<a>      "+selectedLayers+"  "+selectedTaxa+"  "+selectedIndicators+"</a><br>";
            resultHTML += "<b>Total de especímenes que cumplen los criterios:</b><br>";
            resultHTML += "<a>      "+totalCount+"</a>";
            document.getElementById('resultsPanel').innerHTML = resultHTML;
        },

        //If XHR call is not successful
        failure: function(oResponse) {
            YAHOO.log("Failed to process XHR transaction.", "info", "example");
        }

        //Timeout -- if more than 7 seconds go by, we'll abort
        //timeout: 7000
    };

    //Make our XHR call using Connection Manager's
    YAHOO.util.Connect.asyncRequest('GET', sUrl, callback2);
}