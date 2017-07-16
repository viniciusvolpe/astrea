(function (){
    'use strict';

    angular
        .module('avaliacandidatos')
        .filter('bday', bdayFilter);
    
    bdayFilter.$inject = [];

    function bdayFilter(){
        return function (value){
            if(!value) return;
            return value.birthDay + "/" + value.birthMonth + "/" + value.birthYear;
        }
    }
})();