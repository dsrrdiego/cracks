> Docker
+ sudo mkdir -p /sys/fs/cgroup/devices
+ sudo mount -t cgroup -o devices cgroup /sys/fs/cgroup/devices
+ sudo dockerd

+ sudo docker build -t dock.jar .
+ sudo docker run -d -p 8080:8080 dock.jar