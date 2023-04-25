  # **Projet ROBI_server_client**  

Ce document est rédigé en français et présente les deux projets liés ROBI_server_client : Serveur_robi et client_robi.

> ## **Auteurs**
>>  --Shufang Zhang  
--Aymane

>>--Date dernier modifie: 07-04-2023  
--Université Bretagne Occidentale projet L3

> ## **Contenu**

Le projet ROBI_server_client se compose de deux parties : Serveur_robi et client_robi.

>> ### **Serveur_robi**
Le projet Serveur_robi est constitué de **deux packages** : exo4 et Serveur.

>>> #### **Package exo4**
Ce package contient les **classes** suivantes :

* **Exercice4_2_0** :  
  -- Cette classe est le point d'entrée du programme. Elle contient une méthode main qui crée un objet GSpace et exécute les commandes correspondantes en analysant l'expression S donnée en entrée.   
 -- Cette classe utilise les classes Environment et Interpreter pour exécuter les commandes. En interprétant et en exécutant les commandes, elle permet de manipuler l'interface graphique.
* **Environment** :  
 -- Cette classe est une classe d'environnement qui permet de stocker et de gérer les variables définies dans le programme et leurs références.   
 -- Elle utilise un HashMap pour stocker les variables et leurs références. Le nom de la variable est utilisé comme clé du HashMap et la référence de la variable est utilisée comme valeur.   
 -- Elle fournit également des méthodes pour ajouter et supprimer des variables et leurs références, ainsi que pour obtenir la référence correspondant au nom de la variable. C'est une structure de données clé pour l'interprétation des expressions dans le programme.
* **Interpreter** :  
-- Cette classe implémente un interpréteur qui interprète et exécute les expressions de l'arbre syntaxique. 
-- Lorsqu'un environnement et un nœud de l'arbre syntaxique sont donnés, la méthode compute() extrait les informations du nœud (par exemple, l'objet cible et les arguments) et appelle les méthodes correspondantes pour exécuter l'opération représentée par le nœud. 
-- Plus précisément, elle récupère l'objet cible à partir de l'environnement donné, puis appelle la méthode correspondante de cet objet pour exécuter l'opération.  

 **Les classes suivantes sont également présentes :**  
  * **AddChild :** ajoute un enfant à une Reference
* **AddElement :** ajoute un élément a l’environnement
* **DelElement :** supprime un élément de environnement
* **NewElement :** crée une instance d’élément
* **NewImage :** crée une image
* **NewString :** crée une zone de texte
* **SetColor :** change la couleur d'un Element
* **SetDim :** change les dimensions d'un Element
* **Sleep :** pause pendant un certains temps
* **Translate :** déplace un Element
>>> #### **Package Serveur**
Ce package contient les **classes** suivantes :

* **AnsweringMachine** :   
-- Cette classe est un thread qui écoute sur une socket réseau et répond aux messages envoyés par les clients.  
 -- Le constructeur prend en entrée un ServerSocket et un thread principal, puis utilise la méthode accept() pour attendre une connexion de client.   
 -- Lorsqu'un client se connecte, il lit le message à partir du flux d'entrée. Si le message est "stop", il interrompt le thread principal ; sinon, il envoie un message de réponse "Parser is busy" au client.
* **InitSpace** :   
Cette classe permet de créer un espace graphique et d'initialiser un environnement de sorte qu'il soit possible d'ajouter, de supprimer et de modifier des éléments graphiques dans l'espace, ainsi que d'ajouter des types d'éléments graphiques
* **Classe ParserCall** :  
 -- Cette classe implémente la classe Java Thread et est utilisée pour analyser et exécuter un programme Lisp dans un nouveau thread.  
  -- La méthode run() utilise le parseur SParser et l'interpréteur Interpreter pour analyser le programme et l'exécuter en tant que liste de nœuds SNode.   
 --  Si le thread reçoit un signal d'interruption, il arrête l'exécution et retourne.
* **Classe Main** :  
 --- Cette classe est utilisée pour implémenter un serveur Robi. Elle écoute les demandes des clients et gérer le traitement concurrentiel via des threads.  
  -- Lorsqu'un programme est reçu d'un client, deux threads sont lancés : un thread d'analyseur pour analyser le programme et un thread de réponse pour renvoyer les résultats au client.   
  -- Le but principal de ce programme est de recevoir des programmes du client, les analyser et renvoyer les résultats au client.

>> ### **Client_Robi**  
Le projet Serveur_robi est constitué de **package**  Client.  
>>> #### **Package Client**  
* **Classe ClientRobiSwing** :  
 cette classe implémente une interface utilisateur graphique de base pour un client. Elle communique avec un serveur distant nommé "ROBI". Les utilisateurs peuvent entrer des programmes ROBI dans le client et les envoyer au serveur pour calculer, puis afficher les résultats du calcul. Le client a également des fonctionnalités pour démarrer et arrêter le serveur.
 > ## **Éléments techniques**  
 Le projet est écrit en Java et utilise des threads pour gérer les requêtes concurrentes. Le serveur écoute les demandes des clients et gérer le traitement concurrentiel, tandis que le client utilise des sockets pour communiquer avec le serveur distant.
 > ## **bilan critique**
 Les choix de conception et de programmation sont difficiles à évaluer sans connaître les exigences et les contraintes du projet. Cependant, l'utilisation de threads pour gérer les demandes concurrentes semble être une bonne pratique. L'utilisation de sockets pour la communication entre le client et le serveur est également une solution courante pour les applications réseau.

 > ## **à faire**
 * **Object Script :**  
  L'exercice 6 mène à la création d'une Référence "script" qui apparemment permet l’exécution d'instructions.
  * **repondu de serveur :**   
  Le serveur réponds mais sans graphic affiché.