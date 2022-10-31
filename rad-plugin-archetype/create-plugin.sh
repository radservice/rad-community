#!/bin/sh
package=$1
plugin=$2
version=$3
if [ -z $package ]; then 
    echo "Usage: create-plugin.sh (package) (plugin name) (rads version)"
elif [ -z $plugin ]; then 
    echo "Usage: create-plugin.sh $package (plugin name) (rads version)"
elif [ -z $version ]; then 
    echo "Usage: create-plugin.sh $package $plugin (rads version)"
else 
    clear
    mvn archetype:generate -DarchetypeGroupId=net.purwana.rads -DarchetypeArtifactId=rad-plugin-archetype -DarchetypeVersion=${version} -DgroupId=$package -DartifactId=$plugin
fi
exit 1
