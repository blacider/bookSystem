module.exports = function(grunt) {

  // Project configuration.
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    watch: {
      scripts: {
        files: ['front/src/js/*.js', 'front/src/css/*.css', 'front/src/templates/*'],
        tasks: ['clean', 'uglify', 'copy'],
        options: {
          spawn: false,
        },
      },
    },
    copy: {
      main: {
        files: [
          {expand: true, cwd: 'front/src/templates/', src: ['**'], dest: 'src/main/webapp/WEB-INF/templates/'},
          {expand: true, cwd: 'front/src/images/', src: ['**'], dest: 'src/main/webapp/images/'},
          {expand: true, cwd: 'front/src/css/', src: ['**'], dest: 'src/main/webapp/css/'},
        ],
      },
    },
    clean: {
      build: ['src/main/webapp/css', 'src/main/webapp/images', 'src/main/webapp/js', 'src/main/webapp/WEB-INF/templates']
    },
    uglify: {
      options: {
        banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
      },
      my_target: {
        files: [{
          expand:true,
          cwd:'front/src/js/',
          src:'**/*.js',
          dest: 'src/main/webapp/js/'
        },]
      }
    },
  });

  // 加载包含 "uglify" 任务的插件。
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-jshint');
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-contrib-clean');
  // 默认被执行的任务列表。
  grunt.registerTask('default', ['clean', 'uglify', 'copy', 'watch']);

};
