const BroccoliSass = require('broccoli-sass-source-maps')(require('sass'));
const sassGlobImporter = require('node-sass-glob');
const Autoprefixer = require('broccoli-autoprefixer');
const mergeTrees = require('broccoli-merge-trees');
const AssetRev = require('broccoli-asset-rev');
const uglify = require('broccoli-uglify-sourcemap');
const concat = require('broccoli-concat');

const isProduction = process.argv[2] === 'build';

const stylesheetsDir = 'src/assets/stylesheets';
const javascriptsDir = 'src/assets/javascripts';
const imagesDir = 'src/assets/images';

var stylesheetTrees;
var javascriptTrees = [];
var imageTree = imagesDir;

const stylesheetsFiles = [
  'application',
  'login',
  'admin',
];

const javascriptFiles = {
  'admin.js': [
    'admin/lib/**/*.js',
    'admin/global/**/*.js',
    'admin/**/*.js',
  ],
  'application.js': [
    'frontend/global/**/*.js',
    'frontend/**/*.js',
  ],
};

const sassOptions = {
  outputStyle: isProduction ? 'compressed' : 'expanded',
  sourceComments: !isProduction,
  importer: sassGlobImporter,
};

const autoprefixerOptions = {
  browsers: [
    'last 2 versions',
  ]
};

stylesheetTrees = stylesheetsFiles.map(function (filename) {
  var tree = new BroccoliSass([stylesheetsDir], filename + '.scss', filename + '.css', sassOptions);
  return new Autoprefixer(tree, autoprefixerOptions);
});

for (var outputFile in javascriptFiles) {
  javascriptTrees.push(concat(javascriptsDir, {
    outputFile: outputFile,
    inputFiles: javascriptFiles[outputFile],
  }));
}

// Compress javascripts
if (isProduction) {
  javascriptTrees = javascriptTrees.map(function (tree) {
    return uglify(tree);
  });
}

var resultTree = mergeTrees([imageTree].concat(stylesheetTrees).concat(javascriptTrees));

// Add checksum to file names
if (isProduction) {
  resultTree = new AssetRev(resultTree, {
    extensions: ['js', 'css', 'png', 'jpg', 'gif'],
    exclude: ['manifest.json'],
    replaceExtensions: ['js', 'css'],
    prepend: '',
    generateAssetMap: true,
    assetMapPath: "manifest.json",
  });
}

module.exports = resultTree;
