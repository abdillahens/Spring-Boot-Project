package com.immobilier.controlleurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.immobilier.config.JwtTokenUtil;
import com.immobilier.dao.AnnonceDAO;
import com.immobilier.dao.CompteDAO;
import com.immobilier.dao.ContratDAO;
import com.immobilier.dao.ReservationDAO;
import com.immobilier.dao.UtilisateurDAO;
import com.immobilier.entities.Annonce;
import com.immobilier.entities.Compte;
import com.immobilier.entities.Contrat;
import com.immobilier.entities.Photo;
import com.immobilier.entities.Reservation;
import com.immobilier.entities.Utilisateur;
import com.immobilier.metiers.FileUploadUtil;
import com.immobilier.metiers.JwtResponse;
import com.immobilier.metiers.Regles;
import com.immobilier.metiers.Role;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@CrossOrigin("*")
public class UtilisateurControllleur {
	
	@Autowired
	private UtilisateurDAO dao;
	
	@Autowired
	private CompteDAO daoCompte;
	
	@Autowired
	private AnnonceDAO daoAnnonce;
	
	@Autowired
	private ContratDAO daoContrat;
	
	@Autowired
	private ReservationDAO daoReservation;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/add-compte")
	public ResponseEntity<?> ajouter(@RequestHeader Map<String, String> headers,@RequestBody Utilisateur newU) {
		
		 String jwtToken = headers.get("authorization");
			
			try {
				
				 Utilisateur u =VerfiyTokensRoles(jwtToken,Role.ADMIN);
				    if(u!=null)
				    {
				    	newU.getCompte().setMot_de_pass(bCryptPasswordEncoder.encode(u.getCompte().getMot_de_pass()));
						return ResponseEntity.ok(dao.save(newU));
				    }
				    
				    return new ResponseEntity<>( "Token not valid", HttpStatus.METHOD_NOT_ALLOWED);
				    
			}catch (IllegalArgumentException e) {
				// TODO: handle exception
				System.err.println(e);
				 return new ResponseEntity<>( "exception in put parametre", HttpStatus.METHOD_NOT_ALLOWED);
			}
			
		

	}

	@PutMapping("/delete-utilisateur/{id}")
	public boolean supprimer(@RequestHeader Map<String, String> headers,@PathVariable Long id) {
		// TODO Auto-generated method stub
		try {
			
			dao.deleteById(id);
			return true;

		}catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			return false;
		}

	}

	@PostMapping("/update-utilisateur")
	public Utilisateur modifier(@RequestHeader Map<String, String> headers,@RequestBody Utilisateur u) {
		// TODO Auto-generated method stub
		return dao.save(u);
	}

	@GetMapping("/utilisateurs")
	public ResponseEntity<?> getAll(@RequestHeader Map<String, String> headers) {
		// TODO Auto-generated method stub

		 String jwtToken = headers.get("authorization");
			
			try {
				
				 Utilisateur u =VerfiyTokensRoles(jwtToken,Role.ADMIN);
				    if(u!=null)
				    {
						return ResponseEntity.ok(dao.findAll());
				    }
				    
				    return new ResponseEntity<>( "Token not valid", HttpStatus.METHOD_NOT_ALLOWED);
				    
			}catch (IllegalArgumentException e) {
				// TODO: handle exception
				System.err.println(e);
				 return new ResponseEntity<>( "exception in put parametre", HttpStatus.METHOD_NOT_ALLOWED);
			}
			
	}
	
	@PostMapping("/add-annonce")
	public ResponseEntity<?> addAnnonce(@RequestHeader Map<String, String> headers,@RequestBody Annonce annonce, @RequestParam("photos") MultipartFile multipartFile) throws IOException {
		
        String jwtToken = headers.get("authorization");
	    
		try {
			
			 Utilisateur u =VerfiyTokensRoles(jwtToken);
			    if(u!=null)
			    {
			    	
			    	  String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			          //user.setPhotos(fileName);
			   
			          String uploadDir = "user-photos/";
			   
			          FileUploadUtil.saveFile(uploadDir, fileName+u.getId(), multipartFile);
			          // annonce.setPhotos(new ArrayList<>().add(new Photo()));
			    	//System.out.println(annonce.getLocation());
			    	annonce.setProprietaire(u);
					return ResponseEntity.ok(daoAnnonce.save(annonce));
			    }
			    
			    return new ResponseEntity<>( "Token not valid", HttpStatus.METHOD_NOT_ALLOWED);
			    
		}catch (IllegalArgumentException e) {
			// TODO: handle exception
			System.err.println(e);
			 return new ResponseEntity<>( "exception in put parametre", HttpStatus.METHOD_NOT_ALLOWED);
		}

	}
	
	@PutMapping("/delete-annonce/{id}")
	public ResponseEntity<?> deleteAnnonce(@RequestHeader Map<String, String> headers,@PathVariable Long id) {
		  
		 String jwtToken = headers.get("authorization");
		 	
			try {
				
				 Utilisateur u =VerfiyTokensRoles(jwtToken);
				    if(u!=null)
				    {
				    	daoAnnonce.deleteById(id);
						return ResponseEntity.ok("deleted succefull");
				    }
				    
				    return new ResponseEntity<>( "Token not valid", HttpStatus.METHOD_NOT_ALLOWED);
				    
			}catch (IllegalArgumentException e) {
				// TODO: handle exception
				System.err.println(e);
				 return new ResponseEntity<>( "exception in put parametre", HttpStatus.METHOD_NOT_ALLOWED);
			}
	}

	@PostMapping("/modify-annonce")
	public ResponseEntity<?> modifierAnnonce(@RequestHeader Map<String, String> headers,@RequestBody Annonce annonce) {
		
		 String jwtToken = headers.get("authorization");
			
			try {
				
				 Utilisateur u =VerfiyTokensRoles(jwtToken);
				    if(u!=null)
				    {
						return ResponseEntity.ok(daoAnnonce.save(annonce));
				    }
				    
				    return new ResponseEntity<>( "Token not valid", HttpStatus.METHOD_NOT_ALLOWED);
				    
			}catch (IllegalArgumentException e) {
				// TODO: handle exception
				System.err.println(e);
				 return new ResponseEntity<>( "exception in put parametre", HttpStatus.METHOD_NOT_ALLOWED);
			}
	}
	
	@PostMapping("/photo/save")
    public ResponseEntity<?> saveUser(@RequestHeader Map<String, String> headers,
            @RequestParam("image") MultipartFile multipartFile) throws IOException {
         
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        //user.setPhotos(fileName);
 
        String uploadDir = "user-photos/";
 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
         
        return ResponseEntity.ok("kfjffhh");
    }
	
	@GetMapping("/annonces")
	public ResponseEntity<?> getAllAnnonces(@RequestHeader Map<String, String> headers) {
		// TODO Auto-generated method stub
		
		 String jwtToken = headers.get("authorization");
			System.out.println(jwtToken);
			try {
				
				 Utilisateur u =VerfiyTokensRoles(jwtToken);
				    if(u!=null)
				    {
				    	List<Annonce> lists = daoAnnonce.findAll();
				    	for (Annonce annonce : lists) {
							for (Reservation reservation: annonce.getReservation() ) {
								if(reservation.getUtilisateur().getId()==u.getId()) {
									annonce.setEstReserver(true);
								}
								else {
									annonce.setEstReserver(false);
								}
							}
						}
						return ResponseEntity.ok(lists);
				    }
				    
				    return new ResponseEntity<>( "Token not valid", HttpStatus.METHOD_NOT_ALLOWED);
				    
			}catch (IllegalArgumentException e) {
				// TODO: handle exception
				System.err.println(e);
				 return new ResponseEntity<>( "exception in put parametre", HttpStatus.METHOD_NOT_ALLOWED);
				 
			}

	}
	
	@PostMapping("/signer-contrat")
	public ResponseEntity<?> signerContrat(@RequestHeader Map<String, String> headers,@RequestBody Contrat contrat) {
		
        String jwtToken = headers.get("authorization");
		
		try {
			
			 Utilisateur u =VerfiyTokensRoles(jwtToken);
			    if(u!=null)
			    {
					return ResponseEntity.ok(daoContrat.save(contrat));
			    }
			    
			    return new ResponseEntity<>( "Token not valid", HttpStatus.METHOD_NOT_ALLOWED);
			    
		}catch (IllegalArgumentException e) {
			// TODO: handle exception
			System.err.println(e);
			 return new ResponseEntity<>( "exception in put parametre", HttpStatus.METHOD_NOT_ALLOWED);
		}
		
	}
	
	@PutMapping("/delete-contrat/{id}")
	public ResponseEntity<?> annulerContrat(@RequestHeader Map<String, String> headers,@PathVariable Long id) {

		String jwtToken = headers.get("authorization");
		
		try {
			
			 Utilisateur u =VerfiyTokensRoles(jwtToken,Role.ADMIN);
			    if(u!=null)
			    {

					daoContrat.deleteById(id);
					return ResponseEntity.ok("Deleted successfull");
				
			    }
			    return new ResponseEntity<>( "Token not valid", HttpStatus.METHOD_NOT_ALLOWED);
		}catch (IllegalArgumentException e) {
			// TODO: handle exception
			System.err.println(e);
			 return new ResponseEntity<>( "exception in put parametre", HttpStatus.METHOD_NOT_ALLOWED);
		}
	}
	
	@GetMapping("/contrats")
	public ResponseEntity<?> getAllContrats(@RequestHeader Map<String, String> headers) {
		// TODO Auto-generated method stub
		String jwtToken = headers.get("authorization");
		try {
			
			 Utilisateur u =VerfiyTokensRoles(jwtToken,Role.ADMIN);
			    if(u!=null)
			    {
			    	
			    	return ResponseEntity.ok(daoContrat.findAll());
				
			    }
			    return new ResponseEntity<>( "Token not valid", HttpStatus.METHOD_NOT_ALLOWED);
		}catch (IllegalArgumentException e) {
			// TODO: handle exception
			System.err.println(e);
			 return new ResponseEntity<>( "exception in put parametre", HttpStatus.METHOD_NOT_ALLOWED);
		}
		
	}
	
	@PostMapping("/add-reservation")
	public ResponseEntity<?> reserver(@RequestHeader Map<String, String> headers,@RequestBody Reservation reservation) {
		
		  String jwtToken = headers.get("authorization");
			
			try {
				
				 Utilisateur u =VerfiyTokensRoles(jwtToken);
				    if(u!=null)
				    {
						return ResponseEntity.ok(daoReservation.save(reservation));
				    }
				    
				    return new ResponseEntity<>( "Token not valid", HttpStatus.METHOD_NOT_ALLOWED);
				    
			}catch (IllegalArgumentException e) {
				// TODO: handle exception
				System.err.println(e);
				 return new ResponseEntity<>( "exception in put parametre", HttpStatus.METHOD_NOT_ALLOWED);
			}
			
	}
	
	@PutMapping("/delete-reservation/{id}")
	public ResponseEntity<?> annulerReservation(@RequestHeader Map<String, String> headers,@PathVariable Long id) {
		
		String jwtToken = headers.get("authorization");
		
		try {
			
			 Utilisateur u =VerfiyTokensRoles(jwtToken);
			    if(u!=null)
			    {
			    	for (Reservation reservation : u.getReservations()) {
						if(reservation.getId_Reservation()==id) {
							daoReservation.deleteById(id);
							return ResponseEntity.ok("Deleted successfull");
						}
					}
			    	 return new ResponseEntity<>( "access denied", HttpStatus.METHOD_NOT_ALLOWED);
				
			    }
			    return new ResponseEntity<>( "Token not valid", HttpStatus.METHOD_NOT_ALLOWED);
		}catch (IllegalArgumentException e) {
			// TODO: handle exception
			System.err.println(e);
			 return new ResponseEntity<>( "exception in put parametre", HttpStatus.METHOD_NOT_ALLOWED);
		}
	}
	
	@GetMapping("/reservations")
	public ResponseEntity<?> getAllReservations(@RequestHeader Map<String, String> headers) {
		// TODO Auto-generated method stub
		String jwtToken = headers.get("authorization");
		 Utilisateur u =VerfiyTokensRoles(jwtToken,Role.ADMIN);
		    if(u!=null)
			return ResponseEntity.ok(daoReservation.findAll());
		
		return  (ResponseEntity<?>) ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	
	@GetMapping("/comptes")
	public ResponseEntity<?> getAllComptes(@RequestHeader Map<String, String> headers) {
		// TODO Auto-generated method stub
		
		String jwtToken = headers.get("authorization");

		 Utilisateur u =VerfiyTokensRoles(jwtToken,Role.ADMIN);
		    if(u!=null)
		    	return ResponseEntity.ok(daoCompte.findAll());
		
		return  (ResponseEntity<?>) ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED);
		
	}
	
	@GetMapping("/infos")
	public ResponseEntity<?> connecter(@RequestHeader Map<String, String> headers) {
	    String jwtToken = headers.get("authorization");
	    System.out.println(jwtToken);
	   Utilisateur u =VerfiyTokensRoles(jwtToken);
	    if(u!=null)
		return ResponseEntity.ok(u);
	
		return  (ResponseEntity<?>) ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED);
		
		
		


		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
public Utilisateur VerfiyTokensRoles(String jwtToken,Role role) {
		
	    String username=null;
		if ( jwtToken != null && jwtToken.startsWith("Bearer ")) {
			
			jwtToken = jwtToken.substring(7);
	    try {
			username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		} catch (IllegalArgumentException e) {
			System.out.println("Unable to get JWT Token");
		} catch (ExpiredJwtException e) {
			System.out.println("JWT Token has expired");
		}
	    List<Compte> c =  daoCompte.findUserByStatusAndName(username);
		if(c.isEmpty() || c.get(0).getUtilisateur().getRole()!=role) return  null;
		return c.get(0).getUtilisateur();
	}
		return null;

   }

public Utilisateur VerfiyTokensRoles(String jwtToken,Role role1,Role role2) {
	
    String username=null;
	if ( jwtToken != null && jwtToken.startsWith("Bearer ")) {
		
		jwtToken = jwtToken.substring(7);
    try {
		username = jwtTokenUtil.getUsernameFromToken(jwtToken);
	} catch (IllegalArgumentException e) {
		System.out.println("Unable to get JWT Token");
	} catch (ExpiredJwtException e) {
		System.out.println("JWT Token has expired");
	}
    List<Compte> c =  daoCompte.findUserByStatusAndName(username);
	if(c.isEmpty() || ( c.get(0).getUtilisateur().getRole()!=role1 && c.get(0).getUtilisateur().getRole()!=role2)) return  null;
	return c.get(0).getUtilisateur();
}
	return null;

}

public Utilisateur VerfiyTokensRoles(String jwtToken) {
		
	    String username=null;
		if ( jwtToken != null && jwtToken.startsWith("Bearer ")) {
			
			jwtToken = jwtToken.substring(7);
	    try {
			username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		} catch (IllegalArgumentException e) {
			System.out.println("Unable to get JWT Token");
		} catch (ExpiredJwtException e) {
			System.out.println("JWT Token has expired");
		}
	    List<Compte> c =  daoCompte.findUserByStatusAndName(username);
		if(c.isEmpty() ) return  null;
		return c.get(0).getUtilisateur();
	}
		return null;

   }



	
	/*public boolean verifYToken() {
		
	}*/
	


}
