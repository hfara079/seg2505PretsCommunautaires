import java.util.List;

import ca.uottawa.eecs.seg2505.objetpret.controlleur.ObjetControlleur;
import ca.uottawa.eecs.seg2505.objetpret.controlleur.PretControlleur;
import ca.uottawa.eecs.seg2505.objetpret.controlleur.UtilisateurControlleur;
import ca.uottawa.eecs.seg2505.objetpret.db.DBFacade;
import ca.uottawa.eecs.seg2505.objetpret.model.Emprunt;
import ca.uottawa.eecs.seg2505.objetpret.model.Objet;
import ca.uottawa.eecs.seg2505.objetpret.model.Utilisateur;

public class Delegateur {  
   /** 
    * La reference au Singleton 
    */ 
    private static Delegateur delegateur= null;  
    /** 
     * La specification d'une DBFacade est requise pour le fonctionnement du systeme 
    */ 
     private static DBFacade dbFacade = null; 
     public static void setDBFacade(DBFacade facade) { 
         dbFacade= facade; 
     }  
     /** 
      * afin d'obtenir le Singleton 
      * @return 
      */ 
      public static Delegateur getInstance() {  
         if (delegateur==null && dbFacade!=null) { 
           delegateur= new Delegateur(dbFacade); 
         }  
         return delegateur; 
      }  
      
      private PretControlleur pretControlleur = null; 
      private ObjetControlleur objetControlleur = null; 
      private UtilisateurControlleur utilisateurControlleur = null; 
      /** 
       * Methode pour assigner une DBFacade au systeme 
       * @param dbFacade une classe qui implemente DBFacade 
       */ 
      private Delegateur(DBFacade dbFacade) { 
          objetControlleur = new ObjetControlleur(dbFacade); 
          pretControlleur = new PretControlleur(dbFacade); 
          utilisateurControlleur = new UtilisateurControlleur(dbFacade); 
      }  

      /** 
       * Methode pour obtenir l'utlisateur courant
       * @return L'utilisateur courant ou null si le login n'est pas fait encore
        */ 
       public Utilisateur getUtilisateurCourant() {  
          return utilisateurControlleur.getUtilisateurCourant(); 
       }
       /** 
       * Methode pour sauvegarder l'utlisateur 
       * @param user L'utilisateur qu'on veut sauvegarder 
       * @return Si l'utilisateur a ete sauvegarder  
        */ 
       public boolean sauvegarderUtilisateur(Utilisateur user) {  
          return utilisateurControlleur.sauvegarderUtilisateur(user); 
       }
     
       /** 
       * Methode pour verifier si le nom d'utilisateur est disponible
       * @param username Le nom d'utilisateur qu'on veut verifier
       * @return Si le nom d'utilisateur est disponible  
        */ 
      public boolean isUsernameAvailable(String username) { 
          return utilisateurControlleur.isUsernameAvailable(username); 
       }


       /** 
       * Methode pour verifier si le email est disponible 
       * @param email Le email qu'on veut verifier
       * @return Si le email est disponible  
        */ 


       public boolean isEmailAvailable(String email) { 
          return utilisateurControlleur.isEmailAvailable(email); 
       }


/** 
       * Methode pour ajouter un objet � notre base de donn�es
       * @param objet L'objet � ajouter
 
        */ 
       public void ajouterObjet(Objet objet) { 
          if(objet != null){
            objetControlleur.ajouterObjet(objet) ;  
           }
       }


/**   * Methode pour avoir la liste des demandes de pr�t (non acceptees) pour les objets de l'utilisateur courant**/
       public List<Emprunt> getDemandesDePret() {    
    	   return pretControlleur.getDemandesDePret();
       }

/**   * Methode pour definir l'etat d'une demande de pr�t comme acceptee ou refusee   * @param demande - l'Emprunt represantant la demande de pret a traiter   * @param accepte - <code>true</code> si la demande est acceptee, <code>false</code> si elle est refusee   **/
       public void setAccepte(Emprunt demande, boolean accepte){   
    	   if(demande!=null /* && le preteur de la demande est l'utilisateur courant*/){        
    		   pretControlleur.setAccepte(demande, accepte);    
    		   }
    	   }
       }