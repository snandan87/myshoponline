<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Shop User Manage</title>  
    <style>
      .name.ng-valid {
          background-color: lightgreen;
      }
      .name.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .name.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }

      .phone.ng-valid {
          background-color: lightgreen;
      }
      .phone.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .phone.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }

    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="UserController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">User Registration Form </span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.user.id" />

						<!-- <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">First Name</label>
                              <div class="col-md-7">
							<input auto-complete ui-items="ctrl.fnames" ng-model="ctrl.userdetail.fname" class="name form-control" placeholder="Enter your First name" required ng-minlength="3">
								
								<md-autocomplete ng-disabled="ctrl.isDisabled" md-selected-item="ctrl.selectedItem" md-search-text-change="ctrl.searchTextChange(ctrl.searchText)" md-search-text="ctrl.searchText" md-selected-item-change="ctrl.selectedItemChange(item)" md-items="item in ctrl.querySearch(ctrl.searchText)" md-item-text="item.display" md-min-length="0" placeholder="What is your favorite US state?">
		       					 <md-item-template>
		         					 <span md-highlight-text="ctrl.searchText" md-highlight-flags="^i">{{item.display}}</span>
		       					 </md-item-template>
		        				<md-not-found>
		         				 No states matching "{{ctrl.searchText}}" were found.
		          					<a ng-click="ctrl.newState(ctrl.searchText)">Create a new one!</a>
		        				</md-not-found>
     						 </md-autocomplete> 
							</div>
						</div>
					</div> -->

					<div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">First Name {{ctrl.errormessage}}</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.userdetail.fname" name="fName" class="name form-control input-sm" placeholder="Enter your First name" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.fName.$error.required">This is a required field</span>
                                      <span ng-show="myForm.fName.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.fName.$invalid">This field is invalid </span>
                                      
                                  </div>
                                  <span  class="has-error" ng-show="ctrl.error" >Name exist</span>
                                  <span  class="has-error" ng-show="ctrl.error" >{{errormessage}}</span>
                              </div>
                          </div>
                      </div>
                       <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Last Name</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.userdetail.lname" name="lName" class="name form-control input-sm" placeholder="Enter your Last name" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.lName.$error.required">This is a required field</span>
                                      <span ng-show="myForm.lName.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.lName.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                        
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Address</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.userdetail.location" class="form-control input-sm" placeholder="Enter your location [This field is validation free]"/>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Phone</label>
                              <div class="col-md-7">
                                  <input type="number" ng-model="ctrl.userdetail.phone" name="phone" class="phone form-control input-sm" placeholder="Enter your Phone" required/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.phone.$error.required">This is a required field</span>
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.userdetail.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Users </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>Name</th>
                              <th>Location</th>
                              <th>Phone</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.userdetails">
                              <td><span ng-bind="u.id"></span></td>
                              <td><span ng-bind="u.fname"></span> <span ng-bind="u.lname"></span></td>
                              <td><span ng-bind="u.location"></span></td>
                              <td><span ng-bind="u.phone"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
      
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/user_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/user_controller.js' />"></script>
  </body>
</html>