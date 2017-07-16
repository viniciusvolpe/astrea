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
        this.delete = _delete;

        function _save(contact){
            return ContactApi.save({}, contact).$promise;
        }

        function _get(){
            return ContactApi.query({}).$promise;
        }

        function _delete(contact){
            return ContactApi.delete({id: contact.id}).$promise;
        }
    }
})();