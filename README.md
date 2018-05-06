# RES - Labo 4
# SILVA / DE BOURGUES

## Description
Ce projet permet de générer automatiquement des emails bidons à une liste de destinataires prédéfinies.
Pour les besoins du projet, nous utilisons un MockMock Server qui est en fait un faux serveur SMTP pour tester les commandes SMTP et l'envoi d'emails.

La page git du MockMock serveur en question est : https://github.com/tweakers-dev/MockMock/
Il se lance très simplement, en local, avec la commande java -jar MockMock.jar -p 2525
Où le -p défini le port d'écoute du service SMTP

## Déploiement :
git clone https://github.com/1Alucard3/HEIGVD-RES-FEE-2018-Labo-SMTP/
Ensuite modifier le fichier config.properties pour adapter les paramètres
Enfin, compiler le fichier Main.java
