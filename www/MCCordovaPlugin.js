console.log("MCCordovaPlugin init");
var exec = require('cordova/exec');

function MCCordovaPlugin() { 
  console.log("MCCordovaPlugin.js: is created");
}

// SUBSCRIBER KEY //
MCCordovaPlugin.prototype.setSubscriberKey = function(subscriberKey, success, error){
  exec(success, error , "MCCordovaPlugin", 'setSubscriberKey', [subscriberKey]);
}
MCCordovaPlugin.prototype.getSubscriberKey = function(success, error){
  exec(success, error , "MCCordovaPlugin", 'getSubscriberKey', []);
}
// ATTRIBUTES //
MCCordovaPlugin.prototype.addAttribute = function(key, value, success, error){
  exec(success, error , "MCCordovaPlugin", 'addAttribute', [key, value]);
}
MCCordovaPlugin.prototype.removeAttribute = function(key, success, error){
  exec(success, error , "MCCordovaPlugin", 'removeAttribute', [key]);
}
// TAGS //
MCCordovaPlugin.prototype.addTag = function(tag, success, error){
  exec(success, error , "MCCordovaPlugin", 'addTag', [tag]);
}
MCCordovaPlugin.prototype.removeTag = function(tag, success, error){
  exec(success, error , "MCCordovaPlugin", 'removeTag', [tag]);
}
// MONITOR LOCATION //
MCCordovaPlugin.prototype.startWatchingLocation = function(success, error){
  exec(success, error , "MCCordovaPlugin", 'startWatchingLocation', []);
}
MCCordovaPlugin.prototype.stopWatchingLocation = function(success, error){
  exec(success, error , "MCCordovaPlugin", 'stopWatchingLocation', []);
}
MCCordovaPlugin.prototype.isWatchingLocation = function(success, error){
  exec(success, error , "MCCordovaPlugin", 'isWatchingLocation', []);
}
// SDK STATE //
MCCordovaPlugin.prototype.getSDKState = function(success, error){
  exec(success, error , "MCCordovaPlugin", 'getSDKState', []);
}
// NOTIFICATION CALLBACK //
MCCordovaPlugin.prototype.onNotification = function( callback ){
  MCCordovaPlugin.prototype.onNotificationReceived = callback;
  exec(function(result){ 
    console.log('result01');
    console.log(result);
    console.log("Notification callback OK") }, function(result){ console.log('result02');
    console.log(result); console.log("Notification callback ERROR") }, "MCCordovaPlugin", 'registerNotification',[]);
}

// DEFAULT NOTIFICATION CALLBACK //
MCCordovaPlugin.prototype.onNotificationReceived = function(payload){
  console.log("Received push notification")
  console.log(payload)
}
// READY //
exec(function(result){   console.log('result1');  console.log(result);  console.log("MCCordovaPlugin Ready OK") }, 
  function(result){     console.log('result2');    console.log(result);    console.log("MCCordovaPlugin Ready ERROR")   }, 
  "MCCordovaPlugin",'ready',[]  );

var MCCordovaPlugin = new MCCordovaPlugin();
module.exports = MCCordovaPlugin;

