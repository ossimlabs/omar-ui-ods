(function() {
    'use strict';
    angular.module('omarApp').service('downloadService', ['stateService', '$stateParams', 'toastr', '$http', downloadService]);

    function downloadService(stateService, $stateParams, toastr, $http) {

        // Sets the initial url values for the mensa service
        var baseUrl,
            downloadContextPath,
            downloadRequestUrl,
            stagerContextPath,
            stagerRequestUrl,
            downloadManager,
            dataManager;

        this.setDownloadServiceUrlProps = function() {

            baseUrl = stateService.omarSitesState.url.base;

            downloadContextPath = stateService.omarSitesState.url.downloadContextPath;
            downloadRequestUrl = baseUrl + downloadContextPath;

            stagerContextPath = stateService.omarSitesState.url.stagerContextPath;
            stagerRequestUrl = baseUrl + stagerContextPath;


        };
        this.setDownloadServiceUrlProps();

        var imageLayerIds;

        var data = {
            'type': 'Download',
            'zipFileName': '',
            'archiveOptions': {
                'type': 'zip'
            },
            'ids':[]
        };

        this.downloadFiles = function(imageId) {

          downloadManager = downloadRequestUrl;
          dataManager = stagerRequestUrl;

          let idList = [];

          if (!imageId) {
            imageLayerIds = $stateParams.layers.split(',');
            imageId = imageLayerIds[0];
            idList  = imageLayerIds;
          }
          else {
            idList = imageId;
          }

          var dm = downloadManager + '/archive/download';

          data.ids = idList;

          $.fileDownload(dm, {
              httpMethod: 'POST',
              dataType: 'text',
              contentType: 'plain/text',
              data: {
                  fileInfo: JSON.stringify(data)
              },
              successCallback: function(url) {
                  toastr.success('Files are being downloaded.', {
                      positionClass: 'toast-bottom-left',
                      closeButton: true,
                      timeOut: 10000,
                      extendedTimeOut: 5000,
                      target: 'body'
                  });
              },
              failCallback: function(responseHtml, url, error) {

                  //Error will occur if type and archiveOptions type is not specified

                  toastr.error('Unable to download with URL = ' + url, {
                      positionClass: 'toast-bottom-left',
                      closeButton: true,
                      timeOut: 10000,
                      extendedTimeOut: 5000,
                      target: 'body'
                  });
              }
          });
        };
    }
})();
