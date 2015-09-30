var app = angular.module('todo-app',['ngRoute', 'ngResource']);

app.config(function($routeProvider, $locationProvider){
	$routeProvider.when('/todos',{
		templateUrl: 'List_ToDo.html',
		controller : 'ToDosController'
	}).when('/todos/:todoId', {
		templateUrl : 'Edit_ToDo.html',
		controller : 'EditToDoController'		
	});
});


app.factory('ToDo', function($routeParams, $resource){
	return $resource('http://localhost:8080/todos/:todoId',
		{todoId : '@todoId'},
		{update: { method:'PUT', params: {todoId : '@todoId'}},
		remove: { method:'DELETE', params: {todoId : '@todoId'}}});
});

app.controller('ToDosController',function($scope, $location, $filter, ToDo){
	$scope.comp = [];
	$scope.incomp = [];
	$scope.todo = new ToDo();

	//Affichage de la liste des tâches
	$scope.todos = ToDo.query(function(){
		for (var i=0;i<$scope.todos.length;i++)
		{
			//Affichage du nombre de tâches terminées
		    if($scope.todos[i].isDone === "Completed"){
		    	$scope.comp.push($scope.todos[i]);
		    }
		    //Affichage du nombre de tâches non terminées
		    else if($scope.todos[i].isDone === "Incomplete"){
		    	$scope.incomp.push($scope.todos[i]);
		    }
		} 
	});	

	//Création d’une tâche
	$scope.newToDo = function(){
		$scope.todo.$save( function(){
			$location.path('todos/');
		});
	};
	
	//Modification d’une tâche (titre, état)
	$scope.editToDo = function(id){
		$location.path('todos/'+id);
	};
	
	//Suppression d’une tâche
	$scope.deleteToDo = function(id){
		$scope.todo = ToDo.get({todoId : id});
		$scope.todo.$remove({todoId : id}, function(){
			$location.path('todos/');
		});
	};
});

app.controller('EditToDoController', function($scope, $routeParams, $location, ToDo){
	//Modification du titre d’une tâche
	//et, Marquer une tâche comme étant terminée
	$scope.todo = ToDo.get({todoId : $routeParams.todoId});
	$scope.editToDo = function(){
		$scope.todo.$update({todoId : $routeParams.todoId}, function() {
			$location.path('todos/');
	    });
	};
});
