# HTTP Server Project

A simple Spring boot project with REST and GraphQL endpoints incorporated.

### Requirements

Before running the project, make sure your system is equipped with the following superhero tools:

- **Java 17:** The wise wizard of the programming world.
- **Gradle:** The sidekick that helps our hero, Java, with its super builds.
- **Docker:** The container ninja, ready to spin up anything!
- **Docker Compose:** The orchestrator, coordinating the superhero team.

### Unleash the Heroes!

1. **Install Docker:**
   Follow the official [Docker installation guide](https://docs.docker.com/engine/install/).

2. **Install Docker Compose:**
   Follow the official [Docker Compose installation guide](https://docs.docker.com/compose/install/linux/).

3. **Install Java:**
   Install OpenJDK 17 using the following command:
   ```bash
   sudo apt install -y openjdk-17-jdk
   ```

### Running the project
1. Choose a directory where to clone the project. You can also create a new one with the following command:
    ```bash
    mkdir http-server-directory
    ```
2. If needed set ownership to the created directory:
    ```bash
    chown -R $USER:$USER http-server-directory
    ```
3. Change directory:
     ```bash
    cd http-server-directory
    ```
4. Clone the project from github:
     ```bash
    git clone https://github.com/ketgjini/http-server
    ```
5. CD to the project directory:
    ```bash
    cd http-server
    ```
6. Make the bash script executable:
    ```bash
    chmod +x run_http_server_app.sh
    ```
7. Run the script with spring profile `test` (REST endpoints *reachable*):
    ```bash
    ./run_http_server_app.sh test
    ```
8. Run the script with spring profile `dev` (REST endpoints *not-reachable*):
    ```bash
    ./run_http_server_app.sh dev
    ```
This will start the project on port `8080`. 

# GraphQL and REST Endpoint Requests Guide

The following guide provides instructions on how to send GraphQL and REST requests to interact with the service. Ensure you have `curl` installed on your system. If not, you can install it on Ubuntu using the following command:

```bash
sudo apt-get install curl
```
In a new terminal window, now you are ready to explore GraphQL and REST with the examples below, using the `curl` command.

## GraphQL Requests

### 1. Create a Service
```bash
 curl http://localhost:8080/api/graphql \
  -X POST \
  -H 'Content-Type: application/json' \
  -d '{
    "query": "mutation { createdService: createService(serviceInput: { id: \"service_id_1\", resources: [ { id: \"resource_id_1\", owners: [ { id: \"owner_id_1_1\", name: \"Owner 1\", accountNumber: \"AC001\", level: 1 } ] } ] }) { id resources { id owners { id name accountNumber level } } } }"
  }'
```
In the terminal where you are running the Java project you will see a message `Creating Service with id service_id_1`.

### 2. Retrieve a Service by ID
```bash
curl http://localhost:8080/api/graphql \
-X POST \
-H 'Content-Type: application/json' \
-d '{"query":"{ service: getServiceById(id: \"service_id_1\") { id resources { id owners { id name accountNumber level } } } }"}'
```
In this case, if you run it after creating the above service, if the cashing works *(which it should)* you won't see any message in your running Java terminal.

### 3. Update a Service by ID
```bash
curl http://localhost:8080/api/graphql \
-X POST \
-H 'Content-Type: application/json' \
-d '{"query":"mutation { updatedService: updateService(id:\"service_id_1\", serviceInput: { id: \"service_id_1\", resources: [ { id: \"resource_id_1\", owners: [ { id: \"owner_id_1_2\", name: \"Owner 2\", accountNumber: \"AC002\", level: 1 } ] } ] }) { id resources { id owners { id name accountNumber level } } }}"}'
```
### 4. Retrieve all Services
```bash
curl http://localhost:8080/api/graphql \
  -X POST \
  -H 'Content-Type: application/json' \
  -d '{"query":"{ services: getAllServices { id resources { id owners { id name accountNumber level } } }}"}'
```
### 5. Create Another Service with Multiple Owners and Resources
```bash
curl http://localhost:8080/api/graphql \
-X POST \
-H 'Content-Type: application/json' \
-d '{"query":"mutation { createdService: createService(serviceInput: { id: \"service_id_2\", resources: [ { id: \"resource_id_2\", owners: [ { id: \"owner_id_1_2\", name: \"Owner 2\", accountNumber: \"AC002\", level: 2 }, { id: \"owner_id_1_3\", name: \"Owner 3\", accountNumber: \"AC003\", level: 3 } ] }, { id: \"resource_id_3\", owners: [ { id: \"owner_id_2_1\", name: \"Owner 4\", accountNumber: \"AC004\", level: 1 }, { id: \"owner_id_2_2\", name: \"Owner 5\", accountNumber: \"AC005\", level: 2 } ] } ] }) { id resources { id owners { id name accountNumber level } } }}"}'
```

## REST endpoints Requests *(in spring profile `test`)*

### 1. Create a Service
```bash
curl http://localhost:8080/api/v1/services -X POST -H 'Content-Type: application/json' -d '{"id": "service_id_3","resources": [{"id": "resource_id_4","owners": [{"id": "owner_id_33","name": "Owner 33","accountNumber": "AC033","level": 3}]}]}'
```
### 2. Retrieve a Service by ID
```bash
curl http://localhost:8080/api/v1/services/service_id_3 -X GET -H 'Content-Type: application/json'
```
### 3. Update a Service by ID
```bash
curl http://localhost:8080/api/v1/services/service_id_3 -X PUT -H 'Content-Type: application/json' -d '{"id": "service_id_3","resources": [{"id": "resource_id_789","owners": [{"id": "owner_id_012","name": "Updated Owner","accountNumber": "AC123","level": 2}]}]}'
```

**Note:** Adjust the request payload in the *create* and *update* examples according to your specific input requirements and change the IDs accordingly.
You can also use **Postman** to test these endpoints, depending on your preference.

***Little side note:*** While testing in Ubuntu (in a VM) I ran into an error when running the *docker compose* file. The error was `requires cpu with AVX support`.
To fix this, either change the mongo `tag` in docker-compose.yml to `mongo:4.4.6` or disable `Hyper-V` on Windows.


