#!/bin/bash

LOKALISE_TOKEN=$1
LOKALISE_PROJECT_ID=$2

if [ -z "$LOKALISE_TOKEN" ] || [ -z "$LOKALISE_PROJECT_ID" ]
then
  echo -e "Lokalise Token & Project ID can't be empty."
  exit 0
fi

echo -e "Starting upload of strings.xml and plurals.xml for default (EN) to Lokalise service.\n"

echo -e  "Trying to run Lokalise command now. \n"

upload_en_strings= lokalise2 file upload --token $LOKALISE_TOKEN --project-id $LOKALISE_PROJECT_ID --file ./app/src/main/res/values/strings.xml --lang-iso en

upload_en_plurals= lokalise2 file upload --token $LOKALISE_TOKEN --project-id $LOKALISE_PROJECT_ID --file ./app/src/main/res/values/plurals.xml --lang-iso en

#$upload_en_strings
#$upload_en_plurals
#
#echo -e "\nUpload complete.\n"
