module.exports = function(grunt) {

  // Project configuration.
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    watch: {
      scripts: {
        files: ['src/js/*.js', 'src/css/*.css', 'src/templates/*'],
        tasks: ['clean', 'uglify', 'copy'],
        options: {
          spawn: false,
        },
      },
    },
    copy: {
      main: {
        files: [
          {expand: true, cwd: 'src/templates/', src: ['**'], dest: 'build/templates/'},
          {expand: true, cwd: 'src/images/', src: ['**'], dest: 'build/images/'},
          {expand: true, cwd: 'src/css/', src: ['**'], dest: 'build/css/'},
        ],
      },
    },
    clean: {
      build: ['build/**']
    },
    'http-server': {
 
        'dev': {
            root: "build/",
            port: 8080,
            host: "0.0.0.0",
            cache: 1000,
            showDir : true,
            autoIndex: true,
            ext: "html",
            runInBackground: true|false,
            logFn: function(req, res, error) { },
            openBrowser : false,
            customPages: {
                "/":"templates/index.html",
                "/readme": "README.md",
                "/readme.html": "README.html"
            }
 
        }
 
    },
    uglify: {
      options: {
        banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
      },
      my_target: {
        files: [{
          expand:true,
          cwd:'src/js/',
          src:'**/*.js',
          dest: 'build/js/'
        },{
          expand:true,
          cwd:'src/lib/',
          src:'**/*.js',
          dest: 'build/js/'
        }]
      }
    }
  });

  // 加载包含 "uglify" 任务的插件。
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-jshint');
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-contrib-clean');
  grunt.loadNpmTasks('grunt-http-server');
  // 默认被执行的任务列表。
  grunt.registerTask('default', ['clean', 'uglify', 'copy', 'http-server', 'watch']);

};