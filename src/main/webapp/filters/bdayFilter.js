(function (){
    'use strict';

    angular
        .module('avaliacandidatos')
        .filter('bday', bdayFilter);
    
    bdayFilter.$inject = [];

    function bdayFilter(){
        return function (value){
            if(!value) return;
            if(!value.birthDay || !value.birthMonth || !value.birthYear) return "Incompleto";
            return value.birthDay + "/" + value.birthMonth + "/" + value.birthYear;
        }
    }
})();