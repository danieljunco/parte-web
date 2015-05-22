angular.module('doctoresWeb',[])
	.controller('DoctoresCtrl',['$http',function($http){
		var self = this;

		self.submit = function(){
			$http.get('http://young-plateau-1011.herokuapp.com/paciente/'+self.paciente.id+'/episodio').then(function(response){
				self.paciente.episodios= response.data.episodios;
				console.log(response.data);
			},function(errResponse){
				console.log('Error en la solicitud');
			});
		}

		self.filtrar = function(){
			var arregloInicio = self.inicio.split("-"),
				inicio= new Date(arregloInicio[0],arregloInicio[1]-1,arregloInicio[2]),
				arregloFin = self.fin.split("-"),
				fin=new Date(arregloFin[0],arregloFin[1]-1,arregloFin[2]),
				arregloProvisional = []
			;
			for(var i =0; i<self.paciente.episodios.length;i++){
				var fechaArreglo = self.paciente.episodios[i].fecha.substring(0,10).split("-");
				var actual = new Date(fechaArreglo[0],fechaArreglo[1]-1,fechaArreglo[2]);
				if(actual>=inicio && actual<=fin){
					arregloProvisional.push(self.paciente.episodios[i]);
				}
			}
			self.paciente.episodios=arregloProvisional;

		}

	}]);