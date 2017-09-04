const BroccoliSass = require('broccoli-sass-source-maps');
const autoprefixer = require('broccoli-autoprefixer');
const mergeTrees = require('broccoli-merge-trees');
const AssetRev = require('broccoli-asset-rev');
const uglify = require('broccoli-uglify-sourcemap');

const isProduction = process.argv[2] === 'build';

const stylesheetsDir = 'src/assets/stylesheets';
const javascriptsDir = 'src/assets/javascripts';
const imagesDir = 'src/assets/images';

var stylesheetTrees;
var javascriptTree = javascriptsDir;
var imageTree = imagesDir;

const stylesheetsFiles = [
  'application',
  'login',
  'admin',
];

const sassOptions = {
  outputStyle: isProduction ? 'compressed' : 'expanded',
  sourceComments: !isProduction,
};

const autoprefixerOptions = {
  browsers: [
    'last 2 versions',
  ]
};

stylesheetTrees = stylesheetsFiles.map(function (filename) {
  var tree = new BroccoliSass([stylesheetsDir], filename + '.scss', filename + '.css', sassOptions);
  return autoprefixer(tree, autoprefixerOptions);
});

// Compress javascripts
if (isProduction) {
  javascriptTree = uglify(javascriptTree);
}

var resultTree = mergeTrees([javascriptTree, imageTree].concat(stylesheetTrees));

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
