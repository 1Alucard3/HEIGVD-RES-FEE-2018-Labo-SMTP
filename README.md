# RES - Labo 4
# SILVA / DE BOURGUES

## Description
Ce projet permet de générer automatiquement des emails bidons à une liste de victimes (destinataires) prédéfinies. Les victimes sont définies dans le fichier config/victims.RES.utf8, le corps des messages des mails est directement pris depuis le fichier config/messages.utf8 et placé aléatoirement à chaque envoi d'email.

Pour les besoins du projet, nous utilisons un MockMock Server qui est en fait un faux serveur SMTP pour tester les commandes SMTP et l'envoi d'emails.
La page git du MockMock serveur en question est : https://github.com/tweakers-dev/MockMock/
Après avoir télécharger le fichier .jar du projet, celui-ci doit être excécuté avant de lancer le projet.
Il se lance très simplement, en local, avec la commande java -jar MockMock.jar -p 2525
Où le -p défini le port d'écoute du service SMTP
Une fois que le MockMock serveur tourne et écoute sur le port SMTP défini, nous pouvons exécuter notre programme et ainsi lancer l'envoi d'email aux victimes.

## Déploiement :
git clone https://github.com/1Alucard3/HEIGVD-RES-FEE-2018-Labo-SMTP/
Ensuite modifier le fichier config.properties pour adapter les paramètres
Enfin, compiler le fichier Main.java
A partir de là on peut consulter l'interface web du MockMock serveur (par défaut port 8282 mais peut être différent si spécifier avec le paramètre -h 8282 au lancement du MockMock server) pour contrôler que le script a bien fait son travail.
