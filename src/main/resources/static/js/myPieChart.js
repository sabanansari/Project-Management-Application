var chartDataStr = decodeHtml(chartData);

var chartJsonArray = JSON.parse(chartDataStr);

var arrayLength = chartJsonArray.length;
var numericData = [];
var labelData = [];

for(var i=0;i<arrayLength; i++){
	numericData[i] = chartJsonArray[i].count;
	labelData[i] = chartJsonArray[i].label;
}

new Chart(document.getElementById("myPieChart"),{
  type: 'pie',
      data: {
      labels: labelData,
      datasets: [{
        label: 'count',
        data: numericData,
        borderWidth: 1
      }]
    },
    options: {
      title:{
		  display:true,
		  text:'Project Statuses'
	  }
    }
});

function decodeHtml(html){
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}
