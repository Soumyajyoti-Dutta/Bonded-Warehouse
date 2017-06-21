angular.module("myApp", ['ui.bootstrap'])
  .controller('myCtrl', function($scope, $http) {

    $http({
      method: "GET",
      url: "https://33c650b118044c24a69dd093650b726d-vp0.us.blockchain.ibm.com:5002/chain"
    }).then(function mySucces(response) {
      $scope.BlockStart = response.data.height;
	  $scope.statusResponse = "No Transactions in current session"

    }, function myError(response) {
      $scope.BlockStart = response.statusText;
    });



    $scope.dynamicPopover = {
      content: 'Hello, World!',
      templateUrl: 'myPopoverTemplate.html',
      title: 'Title'
    };
    
    $scope.dynamicPopover2 = {
      content: 'Hello, World!',
      templateUrl: 'myPopoverTemplate2.html',
      title: 'Title'
    };

    $scope.BlockInfo = {
      content: 'Hello, World!',
      templateUrl: 'BlockInfoTemplate.html',
      title: 'Title'
    };

    $scope.a = $scope.num
    $scope.b = 0
    $scope.num = "Please wait..."

    setInterval(function() {
      $scope.getNum();
    }, 1000)


    $scope.choicesA = [];
	$scope.statusResponse = "No Transactions in current session"

    $scope.getNum = function() {
      $scope.num = "."
      return $http({
        method: "GET",
        url: "https://33c650b118044c24a69dd093650b726d-vp0.us.blockchain.ibm.com:5002/chain"
      }).then(function mySucces(response) {
        $scope.num = response.data.height;

        if ($scope.num > $scope.a) {
		  $scope.statusResponse = ""
          $scope.b = $scope.num - $scope.a;
          for (var i = 0; i < $scope.b; i++) {
            $scope.choicesA.push({
               
            });
          }

        }
        $scope.a = $scope.num;
      }, function myError(response) {
        $scope.num = response.statusText;
      });

    };


    $scope.getBlockHeight = function() {
      $scope.num1 = "Please wait..."
      return $http({
        method: "GET",
        url: "https://33c650b118044c24a69dd093650b726d-vp0.us.blockchain.ibm.com:5002/chain"
      }).then(function mySucces(response) {
        $scope.num1 = response.data.height - $scope.BlockStart;
        $scope.num2 = response.data.height;
      }, function myError(response) {
        $scope.num1 = response.statusText;
      });

    };
    
    $scope.getTotalBlockHeight = function() {
      $scope.num1 = "Please wait..."
      return $http({
        method: "GET",
        url: "https://33c650b118044c24a69dd093650b726d-vp0.us.blockchain.ibm.com:5002/chain"
      }).then(function mySucces(response) {
        $scope.num2 = response.data.height;
      }, function myError(response) {
        $scope.num2 = response.statusText;
      });

    };

    $scope.getBlockInfo = function(blockCount1) {
      $scope.blockCount = blockCount1 + $scope.BlockStart;
      $scope.url1 = "https://33c650b118044c24a69dd093650b726d-vp0.us.blockchain.ibm.com:5002/chain/blocks/" + $scope.blockCount;
      return $http({
        method: "GET",
        url: $scope.url1
      }).then(function mySucces(response) {
		  if(response.data.hasOwnProperty('transactions'))
		  {
			    $scope.tranID = "TransactionID: " + response.data.transactions[0].txid;
				$scope.payloadB64 = $scope.convertB64ToString(response.data.transactions[0].payload);
				$scope.payload = "Payload: " + $scope.payloadB64;
				$scope.timeVal = "DateTime: " + $scope.convertTimestamp(response.data.transactions[0].timestamp.seconds);
				
				
				
		  }
		  else{
		
				
				$scope.tranID = "" 
				$scope.payload = "Block does not contain any Transaction, only Consensus done" 
				$scope.timeVal = "DateTime: " + $scope.convertTimestamp(response.data.localLedgerCommitTimestamp.seconds);
			  
		  }

      }, function myError(response) {
        $scope.tranID = response.statusText;
      });

    };



    $scope.addNewChoice = function() {
      $scope.choicesA.push({

      });
    };

    $scope.clearData = function() {
      $scope.tranID = "";
      $scope.payload = "";

    };


    $scope.convertTimestamp = function(timestamp) {
      var d = new Date(timestamp * 1000), // Convert the passed timestamp to milliseconds
        yyyy = d.getFullYear(),
        mm = ('0' + (d.getMonth() + 1)).slice(-2), // Months are zero based. Add leading 0.
        dd = ('0' + d.getDate()).slice(-2), // Add leading 0.
        hh = d.getHours(),
        h = hh,
        min = ('0' + d.getMinutes()).slice(-2), // Add leading 0.
        ampm = 'AM',
        time;

      if (hh > 12) {
        h = hh - 12;
        ampm = 'PM';
      } else if (hh === 12) {
        h = 12;
        ampm = 'PM';
      } else if (hh === 0) {
        h = 12;
      }

      // ie: 2013-02-18, 8:35 AM	
      time = yyyy + '-' + mm + '-' + dd + ', ' + h + ':' + min + ' ' + ampm;

      return time;
    }

    $scope.convertB64ToString = function(input) {
      var Base64 = {
        _keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
        encode: function(e) {
          var t = "";
          var n, r, i, s, o, u, a;
          var f = 0;
          e = Base64._utf8_encode(e);
          while (f < e.length) {
            n = e.charCodeAt(f++);
            r = e.charCodeAt(f++);
            i = e.charCodeAt(f++);
            s = n >> 2;
            o = (n & 3) << 4 | r >> 4;
            u = (r & 15) << 2 | i >> 6;
            a = i & 63;
            if (isNaN(r)) {
              u = a = 64
            } else if (isNaN(i)) {
              a = 64
            }
            t = t + this._keyStr.charAt(s) + this._keyStr.charAt(o) + this._keyStr.charAt(u) + this._keyStr.charAt(a)
          }
          return t
        },
        decode: function(e) {
          var t = "";
          var n, r, i;
          var s, o, u, a;
          var f = 0;
          e = e.replace(/[^A-Za-z0-9+/=]/g, "");
          while (f < e.length) {
            s = this._keyStr.indexOf(e.charAt(f++));
            o = this._keyStr.indexOf(e.charAt(f++));
            u = this._keyStr.indexOf(e.charAt(f++));
            a = this._keyStr.indexOf(e.charAt(f++));
            n = s << 2 | o >> 4;
            r = (o & 15) << 4 | u >> 2;
            i = (u & 3) << 6 | a;
            t = t + String.fromCharCode(n);
            if (u != 64) {
              t = t + String.fromCharCode(r)
            }
            if (a != 64) {
              t = t + String.fromCharCode(i)
            }
          }
          t = Base64._utf8_decode(t);
          return t
        },
        _utf8_encode: function(e) {
          e = e.replace(/rn/g, "n");
          var t = "";
          for (var n = 0; n < e.length; n++) {
            var r = e.charCodeAt(n);
            if (r < 128) {
              t += String.fromCharCode(r)
            } else if (r > 127 && r < 2048) {
              t += String.fromCharCode(r >> 6 | 192);
              t += String.fromCharCode(r & 63 | 128)
            } else {
              t += String.fromCharCode(r >> 12 | 224);
              t += String.fromCharCode(r >> 6 & 63 | 128);
              t += String.fromCharCode(r & 63 | 128)
            }
          }
          return t
        },
        _utf8_decode: function(e) {
          var t = "";
          var n = 0;
          var r = c1 = c2 = 0;
          while (n < e.length) {
            r = e.charCodeAt(n);
            if (r < 128) {
              t += String.fromCharCode(r);
              n++
            } else if (r > 191 && r < 224) {
              c2 = e.charCodeAt(n + 1);
              t += String.fromCharCode((r & 31) << 6 | c2 & 63);
              n += 2
            } else {
              c2 = e.charCodeAt(n + 1);
              c3 = e.charCodeAt(n + 2);
              t += String.fromCharCode((r & 15) << 12 | (c2 & 63) << 6 | c3 & 63);
              n += 3
            }
          }
          return t
        }
      };
      var decodedString = Base64.decode(input);
      return decodedString;

    }



  });