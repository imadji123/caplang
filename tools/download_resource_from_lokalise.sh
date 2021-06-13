#!/bin/bash

LOKALISE_TOKEN=$1
LOKALISE_PROJECT_ID=$2

if [ -z "$LOKALISE_TOKEN" ] || [ -z "$LOKALISE_PROJECT_ID" ]
then
  echo -e "Lokalise Token & Project ID can't be empty."
  exit 0
fi

echo -e "Starting download to replace strings.xml and plurals.xml from Lokalise service.\n"

echo -e  "Trying to run Lokalise command now. \n"

download_from_lokalise= lokalise2 file download --token $LOKALISE_TOKEN --project-id $LOKALISE_PROJECT_ID --format xml --unzip-to ./tempFolderForLokalise --export-empty-as skip --export-sort a_z --indentation 4sp --include-description=false

$download_from_lokalise

mv ./tempFolderForLokalise/values/strings.xml ./app/src/main/res/values/strings.xml
mv ./tempFolderForLokalise/values/plurals.xml ./app/src/main/res/values/plurals.xml

mv ./tempFolderForLokalise/values-ko/strings.xml ./app/src/main/res/values-ko/strings.xml
mv ./tempFolderForLokalise/values-ko/plurals.xml ./app/src/main/res/values-ko/plurals.xml

mv ./tempFolderForLokalise/values-ja/strings.xml ./app/src/main/res/values-ja/strings.xml
mv ./tempFolderForLokalise/values-ja/plurals.xml ./app/src/main/res/values-ja/plurals.xml

mv ./tempFolderForLokalise/values-id/strings.xml ./app/src/main/res/values-in/strings.xml
mv ./tempFolderForLokalise/values-id/plurals.xml ./app/src/main/res/values-in/plurals.xml

rm -rf ./tempFolderForLokalise/

echo -e "\nDownload complete.\n"
