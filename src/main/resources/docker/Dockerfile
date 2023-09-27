FROM centos:7.9.2009
RUN yum -y update
RUN yum install -y java-11-openjdk java-11-openjdk-devel
RUN yum install iputils
ENTRYPOINT ["/deploy/run.sh"]