var codealikeApp = angular.module('codealikeApp', ['nvd3']);

codealikeApp.controller('userFactsController', function ($scope) {
    $scope.options = {
                chart: {
                    type: 'multiBarChart',
                    height: 450,
                    x: function(d){return d.label;},
                    y: function(d){return d.value;},
                    showControls: false,
                    showValues: true,
                    transitionDuration: 500,
                    xAxis: {
                        showMaxMin: false
                    },
                    yAxis: {
                        axisLabel: 'Values',
                        tickFormat: function(d){
                            return d3.format(',.2f')(d);
                        }
                    },
                    barColor: ['#8CC63F', '#F7A200', '#E01BAB']
                }
            };
    $scope.data = [
                {
                    "key": "Behavior Facts",
                    "values": [
                        {
                            "label" : "Coding" ,
                            "value" : 70
                        } ,
                        {
                            "label" : "Debugging" ,
                            "value" : 20
                        } ,
                        {
                            "label" : "Building" ,
                            "value" : 10
                        }
                    ]
                }
        ];
});