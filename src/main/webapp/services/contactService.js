(function (){
    'use strict';

    angular
        .module('avaliacandidatos')
        .service('ContactService', ContactService);
    
    ContactService.$inject = ['$resource'];

    function ContactService ($resource){
        var ContactApi = $resource('/contacts');

        // Exposed
        this.save = _save;

        function _save(contact){
            return ContactApi.save({}, contact).$promise;
        }
    }
})();