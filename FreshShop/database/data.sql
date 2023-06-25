create database qlns;
use qlns;
INSERT INTO `Loai` VALUES (1,'Rau'),(2,'Củ'),(3,'Quả');

INSERT INTO `NongSan` VALUES (1,1000,'/image/cuCai.jpg','Test','Củ Cải',1,18,1),
							(2,5000,'/image/rauDiepCa.jpg','e','Rau Diếp cá',1,10,1);
                            
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'USER');

INSERT INTO `NguoiDung` VALUES (1,'Nguyen Y Van','$2a$10$SXGnhVQ98WsPFHhrWC3M3ewd2nr7YOeNd2VXzqvyE7PQq8yMoPlIK','0987654321');

INSERT INTO `Nguoi_Role` VALUES (1,1);

INSERT INTO `DonNhap` VALUES (1,'2023-05-11','12',1000,20,1),(2,'2023-05-11','21',5000,10,2);