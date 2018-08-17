## Spring Boot Admin within Kubernetes

This repo is an example of how to deploy Spring Boot Admin inside OpenShift/k8s to manage your Spring Boot Microservices.

### How to use

Just import the two templates into differents projects, e.g.:

```shell
oc create -f https://raw.githubusercontent.com/ricardozanini/spring-boot-admin-k8s/master/openshift/spring-boot-admin.yaml -n <the_admin_project>

oc create -f https://raw.githubusercontent.com/ricardozanini/spring-boot-admin-k8s/master/openshift/temperature-service.yaml -n <the_services_project>
```

Create new apps using these templates via Web Console or command line:

```shell
oc project <the_admin_project>
oc new-app --template=spring-boot-admin

oc project <the_services_project>
oc new-app --template=temperature-service -e SPRING_BOOT_ADMIN_URL=http://spring-boot-admin.<the_admin_project>.svc:8080
```

Navigate to the Spring Boot Admin page and you are going to see something like:

![Spring Boot Admin Dashboard](https://raw.githubusercontent.com/ricardozanini/spring-boot-admin-k8s/master/docs/images/spring-boot-admin-dashboard.png)

Try the Temperature Service Spring Boot application:

```shell
curl http://<hostname>/temperature/c/f/20
68.0
```

There's also the conversion from Fahrenheit to Celsius (try to figure that out :smile:).

### Known Issues

The discovery mechanism of this version (WIP) is not the ideal world. We're relying on services registering itself (per pod) on Spring Admin using their own internal cluster IP.

I'm working with the [Spring Cloud Kubernetes library](https://github.com/spring-cloud/spring-cloud-kubernetes) to have Spring Admin discovery the Spring Boot services using the k8s way.