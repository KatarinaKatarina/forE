var app = angular.module("jwd26zavrsni16", [ 'ngRoute' ]); // zameniti Ime

app.controller("ctrl", function($scope) {

	$scope.appName = "jwd26zavrsni16"; // zamenitiIme

});

app.controller("sobeCtrl", function($scope, $http) {

	var baseUrlSobe = "/api/sobe";

	$scope.sobe = [];

	var getSobe = function() {

		console.log("skupljanje/pretraga");

		$http.get(baseUrlSobe).then(function success(res) {
			$scope.sobe = res.data;
		}, function error(res) {
			alert("Something went wrong!");
		});
	}

	getSobe();

});

app.controller("rezervacijeCtrl", function($scope, $http, $filter) {

	$scope.pokaziTabelu = true; // ako stavis var scope.pokaziTabelu-nece
								// raditi!
	var baseUrlSobe = "/api/sobe";

	$scope.slobodneSobe = [];
	
	$scope.novi = {};
	$scope.novi.datumUlaskaStr = ""; // pazi, input UVEK daje string
	$scope.novi.datumIzlaskaStr = ""; // pazi, input UVEK daje string

	var getSlobodneSobe = function() {

		var config = {
			params : {}
		};

		if (($scope.novi.datumUlaskaStr != "") && ($scope.novi.datumIzlaskaStr != "")){ // bolje
																						// u
																						// jednom
																						// if,
																						// u
																						// suprotnom
																						// baca
																						// Something
																						// went
																						// wrong..
																						// ako
																						// samo
																						// jedan
																						// promenim
			config.params.datumUlaska = $scope.novi.datumUlaskaStr;
			config.params.datumIzlaska = $scope.novi.datumIzlaskaStr;
			console.log($scope.novi.datumIzlaskaStr);
			console.log($scope.novi.datumUlaskaStr);
		}

		$http.get(baseUrlSobe, config).then(function success(res) {
			$scope.slobodneSobe = res.data;

		}, function error(res) {
			$scope.slobodneSobe = [];
			alert("Something went wrong- parameters!");
		});
	}

	// getSlobodneSobe();

	$scope.poziv = function() {
		if($scope.novi.datumIzlaskaStr == "" || $scope.novi.datumUlaskaStr == ""){
		console.log('ceka se na unos drugog parametra.')
		}
		else{
			getSlobodneSobe();
		}
	}

	
	// nova rezervacija

	$scope.novi.imeGosta = "";
	$scope.novi.prezimeGosta = "";
	$scope.novi.sobaId = ""; // uzeti iz poziva fje
	// $scope.novi.sobaTip = ""; //nije neophodno sve atribute uneti
	$scope.novi.placeno = false;

	$scope.rezervisi = function(idSobe) {
		// pravim novu rezervaciju - mozes samo za one sobe koje su slobodne
		// moram jos da preuzmem Id sobe
		$scope.novi.sobaId = idSobe;
		console.log($scope.novi);
		$http.post("api/rezervacije", $scope.novi).then(function success(res) {
			console.log('napravljena rezervacija');
			getSlobodneSobe();
		}, function error(res) {
			alert("Something went wrong! Bad request.");
		});
	}
	
	$scope.rezervacije = [];
	
	var getRezervacije = function() {

		var config = {
			params : {}
		};

		if ($scope.novi.imeGosta != "") {
			config.params.imeP = $scope.novi.imeGosta;
		}

		if ($scope.novi.prezimeGosta != "") {
			config.params.prezimeP = $scope.novi.prezimeGosta;
			console.log($scope.novi.imeGosta);
		}

		$http.get("api/rezervacije", config).then(function success(res) {
			$scope.rezervacije = res.data;

		}, function error(res) {
			alert("Something went wrong- rezervacije bez param!");
		});
	}

	$scope.switchView = function() {
		if($scope.novi.imeGosta == "" || $scope.novi.prezimeGosta == ""){
			alert("Ime i Prezime gosta moraju biti uneti.")
		}
		else{
		$scope.pokaziTabelu = !$scope.pokaziTabelu;
		getRezervacije();
	}
	}
	
	$scope.delete = function(id){
		$http.delete("api/rezervacije" + "/" + id).then(
			function success(res){
				getRezervacije();
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	
	$scope.plati = function(id){
		if($scope.novi.imeGosta == "" || $scope.novi.prezimeGosta == ""){
			alert("Ime i Prezime gosta moraju biti uneti.")
		}
		else{
		$http.post("api/rezervacije" + "/plati/" + id).then(
			function success(res){
				getRezervacije();
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
		}
	}
	

});


app.controller("statistikaCtrl", function($scope, $http) {

	var baseUrlRezervacije = "/api/rezervacije";
	$scope.rezervacije = [];
	
	$scope.imeGosta = "";
	$scope.prezimeGosta = "";
	$scope.isStatistika = true;
	
	var getRezervacije = function() {

		var config = {
			params : {}
		};

		if ($scope.ime != "") {
			config.params.imeP = $scope.imeGosta;
		}

		if ($scope.prezimeGosta != "") {
			config.params.prezimeP = $scope.prezimeGosta;
		}
		if ($scope.isStatistika == true) { // svakako je true, pisala sam ovo
											// IF zbog preglednosti
			config.params.isStatistika = $scope.isStatistika;
		}

		$http.get("api/rezervacije", config).then(function success(res) {
			$scope.rezervacije = res.data;
			console.log($scope.rezervacije);
		}, function error(res) {
			alert("Something went wrong- rezervacije!");
		});
	}
	
	// pravim potrebne promenljive za STATISTIKU
	
	/*$scope.temp = {};
	$scope.uplatiti = 0;
	$scope.uplaceno = 0;*/ //ako ih definisem ovde,a ne unutar else, onda se pri svakom pritisku na dugne 
	//cifre uvecavaju -sabira se
	
	$scope.again = function() {
		if($scope.imeGosta =="" || $scope.prezimeGosta ==""){
			alert('Unesite podatke o gostu.') 
		}else{
			console.log('Racunam...')
			$scope.temp = {};
			$scope.uplatiti = 0;
			$scope.uplaceno = 0;
			
			console.log($scope.rezervacije.length);
			getRezervacije(); // preuzimam niz po gore definisanoj funkciji
			console.log($scope.rezervacije);
			for (var i = 0; i < $scope.rezervacije.length; i++) {
				console.log('Racunam...')
				$scope.temp = $scope.rezervacije[i]; // na scope kacim objekat
														// kojim cu manipulisati
				console.log($scope.temp); // proveravam objekat da li je stigao
				if($scope.temp.placeno){ 
					$scope.uplaceno += $scope.temp.cenaPoNoci * $scope.temp.brojDana;
				} else{
					console.log($scope.temp.cenaPoNoci);
					$scope.uplatiti += $scope.temp.cenaPoNoci * $scope.temp.brojDana;
				}
			}
	}
		}
});



app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : '/app/html/partial/home.html',
		controller : 'ctrl'
	}).when('/sobe', {
		templateUrl : '/app/html/partial/sobe.html',
	}).when('/rezervacije', {
		templateUrl : '/app/html/partial/rezervacije.html'
	}).when('/statistika', {
		templateUrl : '/app/html/partial/statistika.html'
	}).otherwise({
		redirectTo : '/'
	});
} ]);

/*
 * // pomocu new Date(nekiString) prevodis u datum // $filter si dodala gore u
 * kotroleru, gde i $scope da se ucitaju $scope.formattedDatumUlaska =
 * $filter('date')( new Date($scope.datumUlaska), "yyyy-MM-dd");
 * $scope.formattedDatumIzlaska = $filter('date')( new
 * Date($scope.datumIzlaska), "yyyy-MM-dd"); // "yyyy-MM-dd" ovo je format koji
 * mi treba u RESTu, tako da u taj i // prevodim.
 */