function() {
  var env = karate.env;
  if (!env) {
    env = 'QA';
  }

  var config = {
//if env is not specified the following will be the default values
  PetFinderBaseUrl:'https://api.petfinder.com',
  ContentType:'application/json',
 }
  if (env == 'DEV') {
  //Dev Base Url
    config.PetFinderBaseUrl = 'https://api.petfinder.com';
    config.ContentType = 'application/json';
  }

  if (env == 'QA'){
//QA Base Url
    config.PetFinderBaseUrl = 'https://api.petfinder.com';
    config.ContentType = 'application/json';
  }

  karate.log("Environment is ::: "+env+"");
  karate.configure('readTimeout', 6000000);
  karate.configure("connectTimeout", 50000);

  return config;
 }