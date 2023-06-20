const webpack = require('webpack');

module.exports = {
  plugins: [
    new webpack.DefinePlugin({
      $ENV: {
        TODO_APP_URL: JSON.stringify(process.env.TODO_APP_URL)
      }
    })
  ]
};