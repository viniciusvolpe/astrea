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
        this.get = _get;

        function _save(contact){
            return ContactApi.save({}, contact).$promise;
        }

        function _get(){
            return ContactApi.query({}).$promise;
        }
    }
})();