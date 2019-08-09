yayQuery = function() {
  var yay = {};
  yay.sayHello = function(message) {
      console.log(message);
  };
  yay.getMessage = function() {
      return 'yay foregin Hello, world!';
  };
  return yay;
};