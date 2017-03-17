;(function($, window, document,undefined) {

    //$clientURL = "http://localhost:8080/eme/";
    $clientURL = "/mca/api/";
    $.EMEClient = function (path,opt,callback) {
      var defaults = {
                    'url':        $clientURL+path,
                    'type':        'GET',
                    'dataType':    'json',
                    'contentType': 'application/json; charset=UTF-8',
                    'data':         {},
                     headers:      {} 
        };
     var options = $.extend({}, defaults, opt);
       $.ajax({
                    url:          options.url,
                    type:         options.type,
                    dataType:     options.dataType,
                    contentType:  options.contentType,
                    data:         options.data,
                    headers:      options.headers,
                    success: function(data, textStatus){
                         callback(data);

                       }
                 });


    };


     
     $.getAlternativeVls = function(problemId,callback){
             $.EMEClient('getAlternativeVls',{data:{"problemId":problemId}},callback);
 
     };

  
     
     
})(jQuery, window, document);