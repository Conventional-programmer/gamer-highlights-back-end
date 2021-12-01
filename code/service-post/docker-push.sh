dockerName=caskamp/gamehighlights-post-service
docker build -t "$dockerName":latest . && docker push "$dockerName":latest && docker build -t "$dockerName":main . && docker push "$dockerName":main
