(function(){
    'use strict';
    angular.module('LunchCheck',[])
    .controller('LunchController',LunchController);
    
    LunchController.$inject=['$scope'];
function LunchController($scoope){
    $scoope.textBox="";
    $scoope.myMessage =0;

    $scoope.lunchNumber=function(){
        var totalLunchValue =0;
        totalLunchValue=calculateNumberOfLunch($scoope.textBox);
        if ($scoope.textBox ==="")
        $scoope.myMessage= "Please enter data first!";

        else if ((totalLunchValue <=3) &&((totalLunchValue>0)))
        $scoope.myMessage= "Enjoy!";
        else
        $scoope.myMessage= "Too much!";

    };
function calculateNumberOfLunch(string){
    var totalStringValue = 0 ;
    var  word = string.split(",").filter(n=>n);


    totalStringValue = word.length;
    return totalStringValue;
};
}

    
})();