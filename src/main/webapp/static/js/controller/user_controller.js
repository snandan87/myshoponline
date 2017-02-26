'use strict';

angular.module('myApp').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
    var self = this;
    self.userdetail={id:null,fname:'',lname:'',location:'',phone:''};
    self.userdetails=[];
    self.fnames=[];
    self.error;
    $scope.errormessage;
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;

    fetchAllFName();
    fetchAllUsers();

    function fetchAllUsers(){
        UserService.fetchAllUsers()
            .then(
            function(d) {
                self.userdetails = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
    
    function fetchAllFName(){
        UserService.fetchAllFname()
            .then(
            function(d) {
                self.fnames = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }

    function createUser(userdetail){
        UserService.createUser(userdetail)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while creating User');
                self.error=true;
                $scope.errormessage=errResponse.data.errorMessage;
                console.log($scope.errormessage);
            }
        );
    }

    function updateUser(userdetail, id){
        UserService.updateUser(userdetail, id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while updating User');
            }
        );
    }

    function deleteUser(id){
        UserService.deleteUser(id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    }

    function submit() {
        if(self.userdetail.id===null){
            console.log('Saving New User', self.userdetail);
            createUser(self.userdetail);
        }else{
            updateUser(self.userdetail, self.userdetail.id);
            console.log('User updated with id ', self.userdetail.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.userdetails.length; i++){
            if(self.userdetails[i].id === id) {
                self.userdetail = angular.copy(self.userdetails[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.userdetail.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteUser(id);
    }


    function reset(){
        self.user={id:null,fname:'',lname:'',location:'',phone:''};
        $scope.myForm.$setPristine(); //reset Form
    }
    


    // list of `state` value/display objects
    self.states        = loadAll();
    self.querySearch   = querySearch;
    self.simulateQuery = false;
    self.isDisabled    = false;
    self.selectedItemChange = selectedItemChange;
    self.searchTextChange   = searchTextChange;

    self.newState = newState;

    function newState(state) {
      alert("Sorry! You'll need to create a Constitution for " + state + " first!");
    }
    
    function searchTextChange(text) {
    	console.log('Text changed to ' + text);
      }

      function selectedItemChange(item) {
    	  console.log('Item changed to ' + JSON.stringify(item));
      }
   
    function querySearch (query) {
      var results = query ? self.states.filter( createFilterFor(query) ) : self.states,
          deferred;
      if (self.simulateQuery) {
        deferred = $q.defer();
        $timeout(function () { deferred.resolve( results ); }, Math.random() * 1000, false);
        return deferred.promise;
      } else {
        return results;
      }
    }

    
    /**
     * Build `states` list of key/value pairs
     */
    function loadAll() {
    	console.log("loading");
      var allStates = 'Alabama, Alaska, Arizona, Arkansas, California, Colorado, Connecticut, Delaware,\
              Florida, Georgia, Hawaii, Idaho, Illinois, Indiana, Iowa, Kansas, Kentucky, Louisiana,\
              Maine, Maryland, Massachusetts, Michigan, Minnesota, Mississippi, Missouri, Montana,\
              Nebraska, Nevada, New Hampshire, New Jersey, New Mexico, New York, North Carolina,\
              North Dakota, Ohio, Oklahoma, Oregon, Pennsylvania, Rhode Island, South Carolina,\
              South Dakota, Tennessee, Texas, Utah, Vermont, Virginia, Washington, West Virginia,\
              Wisconsin, Wyoming';

      return allStates.split(/, +/g).map( function (state) {
        return {
          value: state.toLowerCase(),
          display: state
        };
      });
    }

    /**
     * Create filter function for a query string
     */
    function createFilterFor(query) {
      var lowercaseQuery = angular.lowercase(query);

      return function filterFn(state) {
        return (state.value.indexOf(lowercaseQuery) === 0);
      };

    }
    
   

}]);

