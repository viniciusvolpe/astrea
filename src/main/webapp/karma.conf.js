// Karma configuration
// Generated on Sun Jul 16 2017 12:47:21 GMT-0300 (Hora oficial do Brasil)

module.exports = function(config) {
  config.set({

    // base path that will be used to resolve all patterns (eg. files, exclude)
    basePath: '',


    // frameworks to use
    // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
    frameworks: ['jasmine'],


    // list of files / patterns to load in the browser
    files: [
      'https://ajax.googleapis.com/ajax/libs/angularjs/1.3.8/angular.min.js',
      'https://ajax.googleapis.com/ajax/libs/angularjs/1.3.8/angular-mocks.js',
      'https://ajax.googleapis.com/ajax/libs/angularjs/1.3.8/angular-resource.min.js',
      'https://ajax.googleapis.com/ajax/libs/angularjs/1.3.8/angular-route.min.js',
      'http://angular-ui.github.io/ui-router/release/angular-ui-router.js',
      'http://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/0.12.0/ui-bootstrap-tpls.min.js',
      'libs/angular-toastr/dist/angular-toastr.min.js',
      'app/main.js',
      'controllers/*.js',
      'services/*.js',
      'tests/**/*.test.js'
    ],


    // list of files to exclude
    exclude: [
    ],


    // preprocess matching files before serving them to the browser
    // available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor
    preprocessors: {
    },


    // test results reporter to use
    // possible values: 'dots', 'progress'
    // available reporters: https://npmjs.org/browse/keyword/karma-reporter
    reporters: ['progress'],


    // web server port
    port: 9876,


    // enable / disable colors in the output (reporters and logs)
    colors: true,


    // level of logging
    // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
    logLevel: config.LOG_INFO,


    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,


    // start these browsers
    // available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
    browsers: ['Chrome'],


    // Continuous Integration mode
    // if true, Karma captures browsers, runs the tests and exits
    singleRun: false,

    // Concurrency level
    // how many browser should be started simultaneous
    concurrency: Infinity
  })
}
