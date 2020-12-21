import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author GUINDO Mouctar Ousseini
 * @author SAIAH Nadir
 * @author NGUYEN Danh
 *
 */
public class Controleur {
	Gala gala;
	LocalDate dateC;
	Console console =new Console();
	public Controleur(LocalDate d ) throws IOException {
		gala=new Gala(d, "etudiants.csv", "personnel.csv");
		dateC=d;
	}

	public int ctlAcceuil() {
		console.acceuil();
		return console.choixAcceuil();
	}
	/**
	 * 
	 * @param num
	 * @return l'etudiant dont le numeros est passe en param�tre
	 */
	public Etudiant ctlgetEtudiant(int num) {


		if(gala.listeEtu.containsKey(num))return gala.listeEtu.get(num);
		else {
			throw new IllegalArgumentException("Desoler votre Id est incorect");
		}

	}
	/**
	 * 
	 * @param num
	 * @return le personnel dont le numero est passe en 
	 * param�tre
	 */
	public Personnel ctlgetPerso(int num) {

		if (gala.listePers.containsKey(num))return gala.listePers.get(num);
		else {
			throw new IllegalArgumentException("Desoler votre Id est incorrect");
		}


	}
	/**
	 * 
	 * @param n
	 * @param cpt
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 *cette methode est le controleur principale
	 * qui controle les valeurs transmis par l'utilisateur � traver
	 * le console qui appelle aussi des autres fonctions du controleur
	 * pour assurer le bon fonctionnemnt du programme et g�re les exceptions.
	 */
	public void ctlPrincipale(int n,int cpt) throws IOException, ClassNotFoundException {
		String inv="";
		int nbp=0;
		boolean estEntier=true;

		File f1=new File("gala.ser");
		if(cpt==0 && fichierExiste(f1))gala=ctlChargerGala(gala);
		
		if(gala.unMoisAvGala()&& gala.listAttente!=null) {
			gala.demandeAccepter();
		}
		if(n==1) {
			int c=console.getId();
			console.AffichageToStringPers(ctlgetPerso(c));
			if(!gala.listPersInscrit.containsKey(c)) {
				int r=0;

				do {

					do {
						estEntier=true;
						try {
							r=console.etatRservation();
						} catch (InputMismatchException e) {
							console.formatIncorrect();						
							estEntier=false;
						}

						if(r!=1 && r!=2 && r!=3) {
							console.choixImpossi();
						}
					}while(r!=1 && r!=2 && r!=3 || !estEntier);
					switch (r) {
					case 1:
						int ger=0;
						gala.listPersInscrit.put(c,ctlgetPerso(c));
						do {
							estEntier=true;
							try {
								ger=console.choixGerer();
							} catch (InputMismatchException e) {
								console.formatIncorrect();
								estEntier=false;
							}

							if(ger!=1 && ger!=2 && ger!=3)console.choixImpossi();
						}while((ger!=1 && ger!=2 && ger!=3) || !estEntier);

						int r1=0;
						if(ger==1) {
							console.nbPlaceRe(2);
							do {
								estEntier=true;
								try {
									r1 =console.choixRPers();
								} catch (InputMismatchException e) {
									console.formatIncorrect();
									estEntier=false;
								}

								if(r1!=1 && r1!=2)console.choixImpossi();
							}while(r1!=1 && r1!=2 || !estEntier);
							if(r1==1) {
								int numTable=0;
								do {
									if(gala.getNbPlacePerso()<gala.getNbPlaceTotalP()) {
										console.planReserPers(gala.infoInscritPers());
									}
									do {
										estEntier=true;
										try {
											numTable=cltnumTablePers(console.numTableSouhaiterPers());

											if (numTable==-1) {
												console.choixImpossi();
											}else
												nbp=ctlchoixNbPLace(console.nbPlaceSouhaiter());
											if (nbp==-1) {
												console.choixImpossi();
											}
										} catch (InputMismatchException e) {
											estEntier=false;
											console.formatIncorrect();
										}

									}while(numTable==-1 || nbp==-1 || !estEntier);

									if(!gala.placeDispoPers(nbp)) {
										console.pasDePlace();
										break;
									}
									inv=console.inFoInviter(nbp-1);

									if(gala.possedeAssezPlace(numTable, nbp)) {
										ctlgetPerso(c).reservationPers(LocalDate.now(), numTable, nbp);
										gala.listeTable.get(numTable).prendPlace(nbp);
										ctlgetPerso(c).InfoRerserPers(inv);
										gala.setNbPlacePerso(gala.getNbPlacePerso()-nbp);
										gala.listReserPers.add(ctlgetPerso(c));

										int num=ctlgetPerso(c).numTableReservation();
										int nbp1=ctlgetPerso(c).getNbPlaceRerv();
										console.infoReservPers(num,nbp1);
										int prix=gala.getTarifPers(c, nbp1);
										console.prixTotale(prix);
										break;
									}
									else {
										console.pasAsserDePlace(numTable);
									}

								}while(!gala.possedeAssezPlace(numTable, nbp));

							}else if(r1==2) {
								boolean b=false;
								do {
									estEntier=true;
									try {
										nbp=ctlNbPlaceSouhaiter(console.nbPlaceSouhaiter());
									} catch ( InputMismatchException e) {
										estEntier=false;
										console.formatIncorrect();
									}	

									if(!gala.placeDispoPers(nbp)) {

										break;
									}
									if(nbp==-1) {
										console. nbPlaceRe(2);
									}
								}while(nbp==-1 || !estEntier);
								if (!gala.placeDispoPers(nbp)) {
									console.pasDePlace();
									break;
								}
								int numTable=gala.attribuerTable(nbp);
								ctlgetPerso(c).reservationPers(LocalDate.now(), numTable, nbp);
								gala.listeTable.get(numTable).prendPlace(nbp);
								gala.setNbPlacePerso(gala.getNbPlacePerso()-nbp);

								inv=console.inFoInviter(nbp-1);
								ctlgetPerso(c).InfoRerserPers(inv);
								gala.listReserPers.add(ctlgetPerso(c));

								int num=ctlgetPerso(c).numTableReservation();
								int nbp1=ctlgetPerso(c).getNbPlaceRerv();
								console.infoReservPers(num,nbp1);
								int prix=gala.getTarifPers(c, nbp1);
								console.prixTotale(prix);

							}

						}

						else if(ger==2) {
							gala.desinscrirePers(c);
						}
					case 2:
						break;
					default:
						console.choixImpossi();

					}
					if (r!=1 && r!=2) {
						console.choixImpossi();
					}
				}while(r!=1 && r!=2);

			}else if(!ctlgetPerso(c).aReserver()) {
				int ger=0;
				do {
					estEntier=true;
					try {
						ger=console.choixGerer();
					} catch (InputMismatchException e) {
						estEntier=false;
						console.formatIncorrect();
					}
					if (ger!=1 && ger !=2) {
						console.choixImpossi();
					}
				}while((ger!=1 && ger !=2) || !estEntier);
				if(ger==1) {
					int r=0;
					do {
						estEntier=true;
						try {

							console.nbPlaceRe(2);
							r =console.choixRPers();

						} catch (InputMismatchException e) {
							estEntier=false;
							console.formatIncorrect();						}
					}while(r!=1 && r!=2 || !estEntier);
					if(r==1) {
						int numTable=0;
						do {
							estEntier=true;
							try {
								if(gala.listReserPers.size()!=0)
									console.planReserPers(gala.infoInscritPers());

								numTable=cltnumTablePers(console.numTableSouhaiterPers());
								nbp=ctlNbPlaceSouhaiter(console.nbPlaceSouhaiter());
								if(!gala.placeDispoPers(nbp)) {
									console.pasDePlace();
									break;
								}
								inv=console.inFoInviter(nbp-1);
								if(gala.possedeAssezPlace(numTable, nbp)) {
									ctlgetPerso(c).reservationPers(LocalDate.now(), numTable, nbp);
									gala.listeTable.get(numTable).prendPlace(nbp);
									ctlgetPerso(c).InfoRerserPers(inv);
									gala.listReserPers.add(ctlgetPerso(c));
									break;
								}
								else {
									console.pasAsserDePlace(numTable);
								}
							}catch (InputMismatchException e) {
								estEntier=false;
								console.formatIncorrect();
							}
						}while(!gala.possedeAssezPlace(numTable, nbp) || !estEntier);

					}else if(r==2) {
						do {
							estEntier=true;
							try {
								nbp=ctlchoixNbPLace(console.nbPlaceSouhaiter());
								if(!gala.placeDispoPers(nbp)) {
									console.pasDePlace();
									break;
								}

							} catch (InputMismatchException e) {
								estEntier=false;
								console.formatIncorrect();
							}


						}while(nbp==-1 || !estEntier);
						if(!gala.placeDispoPers(nbp)) {
							throw new IllegalArgumentException();
						}
						int numTable=gala.attribuerTable(nbp);

						ctlgetPerso(c).reservationPers(LocalDate.now(), numTable, nbp);
						gala.listeTable.get(numTable).prendPlace(nbp);

						inv=console.inFoInviter(nbp-1);
						int num=ctlgetPerso(c).numTableReservation();
						int nbp1=ctlgetPerso(c).getNbPlaceRerv();
						gala.listReserPers.add(ctlgetPerso(c));
						console.infoReservPers(num,nbp1);
						int prix=gala.getTarifPers(c, nbp1);
						console.prixTotale(prix);

					}

				}
				else if(ger==2) {
					gala.desinscrirePers(c);
				}
			}else {
				int a=0;
				do {
					estEntier=true;
					try {
						int num=ctlgetPerso(c).numTableReservation();
						int nbp1=ctlgetPerso(c).getNbPlaceRerv();
						console.infoReservPers(num,nbp1);
						int prix=gala.getTarifPers(c, nbp1);
						console.prixTotale(prix);

						a= console.annulerReser();
						if(a==1) {
							if(gala.annulerReserPossible()) {
								gala.supprimerReserPers(c);
							}else {
								console.impossAnn();
								break;
							}
						}
						else if(a==2)break;

					} catch (InputMismatchException e) {

						estEntier=false;
					}
					if (!estEntier) {
						console.formatIncorrect();
					}
				}while(a!=1 && a!=2|| !estEntier);
			}

		}
		else if(n==2) {
			int c=console.getId();
			int numTalbe=0;
			
			if(!gala.listEtuInscrit.containsKey(c)) {
				console.AffichageToStringEtu(ctlgetEtudiant(c));
				int r=0;
				do {
					estEntier=true;
					try {
						r=console.etatRservation();
						if(r!=1 && r!=2)console.choixImpossi();
					} catch (InputMismatchException e) {
						estEntier=false;
					}

				}while(r!=1 && r!=2 || !estEntier);
				switch (r) {
				case 1:
					gala.listEtuInscrit.put(c,ctlgetEtudiant(c));
					int ger=0;
					do {
						estEntier=true;
						try {
							ger=console.choixGererE();
							if (ger!=1 && ger!=2 && ger!=3) {
								console.choixImpossi();
							}
						} catch (InputMismatchException e) {
							estEntier=false;
							console.formatIncorrect();
						}

					}while(ger!=1 && ger!=2 && ger!=3 || !estEntier);


					if(ger==1) {
						do {
							estEntier=true;
							try {

								if(gala.getNiveau(c)<5)console.nbPlaceRe(2);
								else console.nbPlaceRe(4);

								nbp=ctlNbPlaceSouhaiter(console.nbPlaceSouhaiter(), ctlgetEtudiant(c));
								if(nbp==-1) {
									console.nbPlaceImpo();
								}
							} catch (InputMismatchException e) {
								estEntier=false;
								console.formatIncorrect();
							}


						}while(nbp==-1 || !estEntier);

						ctlgetEtudiant(c).setnbPlacRerv(nbp);
						if(ctlgetEtudiant(c).getNivFormation()==5) {
							gala.listAttente.addFirst(c);
						}else gala.listAttente.add(c);
						
					}
					else if(ger==2) {
						gala.desinscrireEtu(c);
					}

					break;
				case 2:

					break;

				default:
					break;
				}


			}else if(ctlgetEtudiant(c).getNbPlaceRerv()==0) {
				int ger=0;
				int np=0;
				do {
					estEntier=true;
					try {
						ger=console.choixGererE();
						if (ger!=1 && ger!=2 && ger!=3) {
							console.choixImpossi();
						}
					} catch (InputMismatchException e) {
						estEntier=false;
						console.formatIncorrect();
					}

				}while(ger!=1 && ger!=2 && ger!=3 || !estEntier);
				if(ger==1) {
					do {
						estEntier=true;
						try {

							nbp=ctlNbPlaceSouhaiter(console.nbPlaceSouhaiter(), ctlgetEtudiant(c));
							if (nbp==-1) {
								console.choixImpossi();
							}
						} catch (InputMismatchException e) {
							estEntier=false;
							console.formatIncorrect();
						}

					}while(nbp==-1 || !estEntier);

					ctlgetEtudiant(c).setnbPlacRerv(nbp);
					if (ctlgetEtudiant(c).getNivFormation()==5) {
						gala.listAttente.addFirst(c);
					}else gala.listAttente.add(c);
					
				}else if(ger==2) {
					gala.desinscrireEtu(c);
				}


			}		
			else if(gala.lesDemandAcpp!=null && gala.lesDemandAcpp.containsKey(c)
					&& !ctlgetEtudiant(c).isConfirmer()) {
				int p=0;
				int numTa=0;
				int gere=0;
				console.infoReservEtu(ctlgetEtudiant(c).getNbPlaceRerv());

				do {
					estEntier=true;
					try {
						p=console.confirmer();
						if (p!=1 && p!=2) {
							console.choixImpossi();
						}
					} catch (InputMismatchException e) {
						estEntier=false;
						console.formatIncorrect();
					}

				}while(p!=1 && p!=2 || !estEntier);
				if(p==1) {

					do {
						do {
							estEntier=true;
							try {
								gere=console.choixGerer();
							} catch (InputMismatchException e) {
								estEntier=false;
								console.formatIncorrect();
							}
							if (gere!=1 && gere!=2 && gere!=3) {
								console.choixImpossi();
							}

						}while(gere!=1 && gere!=2 && gere!=3 || !estEntier);



						switch (gere) {
						case 1:
							int na =0;
							do {
								do {
									estEntier=true;
									try {
										na =console.choixRPers();
									} catch (InputMismatchException e) {
										estEntier=false;
										console.formatIncorrect();
									}
									if (na!=1 &&na!=2) {
										console.choixImpossi();
									}

								}while(na!=1 && na!=2 || !estEntier);

								if(na==1) {

									if(gala.listReserEtu.size()>0) {
										console.planReserPers(gala.infoInscritEtu());
									}
									boolean b=true;
									do {
										b=true;
										do {
											estEntier=true;
											try {
												numTa=cltnumTableEtu(console.numTableSouhaiterEtu());
												if (numTa==-1) {
													console.choixImpossi();	
												}
											} catch (InputMismatchException e) {
												estEntier=false;
												console.formatIncorrect();
											}
										}while(!estEntier || numTa==-1);

										int nbpe=ctlgetEtudiant(c).getNbPlaceRerv();
										if(gala.possedeAssezPlace(numTa,nbpe)) {
											ctlgetEtudiant(c).reservationEtu(LocalDate.now(),numTa,nbpe);
											gala.listeTable.get(numTa).prendPlace(nbpe);
											ctlgetEtudiant(c).setIsConfirmer(true);
											inv=console.inFoInviter(nbpe-1);
											ctlgetEtudiant(c).InfoRerserEtu(inv);
											gala.listReserEtu.add(ctlgetEtudiant(c));
											break;
										}

										else {
											console.pasAsserDePlace(numTa);
											b=false;
										}
									}while(!b);
								}else if(na==2) {
									int nbpe=ctlgetEtudiant(c).getNbPlaceRerv();
									int numt=gala.attribuerTableEtu(nbpe);
									ctlgetEtudiant(c).reservationEtu(LocalDate.now(),numt,nbpe);
									inv=console.inFoInviter(nbpe-1);
									ctlgetEtudiant(c).InfoRerserEtu(inv);
									gala.listReserEtu.add(ctlgetEtudiant(c));
									gala.listeTable.get(numt).prendPlace(nbpe);
									ctlgetEtudiant(c).setIsConfirmer(true);
									break;
								}
								if(na!=1 &&na!=2) {
									console.choixImpossi();
								}

							}while(na!=1 &&na!=2);

						}
						if (gere==2) {
							gala.listEtuInscrit.remove(c);
						}

					}while(gere!=1 && gere!=2 && gere!=3);

				}
				else if (p==2) {
					if (gala.annulerReserPossible()) {
						gala.listAttente.remove(c);
						gala.listEtuInscrit.remove(c);
						gala.lesDemandAcpp.remove(c);
						gala.listReserEtu.remove(ctlgetEtudiant(c));

						
					}else console.impossAnn();

				}


			}
			else if (!ctlgetEtudiant(c).isConfirmer()) {
				console.attente();

			}
			else {
				int num=ctlgetEtudiant(c).tableEtu();
				int nbpp=ctlgetEtudiant(c).getNbPlaceRerv();
				int prix =gala.getTarifReserEtu(c, nbpp);
				console.infoReservPers(num,nbpp);
				console.prixTotale(prix);
				int a=0;
				do {
					estEntier=true;
					try {
						a= console.annulerReser();
						if(a==1) {
							if(gala.annulerReserPossible()) {
								gala.supprimerReserEtu(c);
								gala.listEtuInscrit.remove(c);
								gala.listAttente.remove(c);
								gala.lesDemandAcpp.remove(c);
								ctlgetEtudiant(c).setIsConfirmer(false);
								gala.listReserEtu.remove(ctlgetEtudiant(c));
								
							}else {
								console.impossAnn();
								break;
							}
						}
						if (a!=1 && a!=2) {
							console.choixImpossi();
						}
						else if(a==2)break;
					} catch (InputMismatchException e) {
						estEntier=false;
						console.formatIncorrect();
					}

				}while(a!=1 && a!=2 || !estEntier);
			}


		}else if(n==3) {
			File f=new File("gala.ser");
			f.createNewFile();
			ctlSauvergarde(gala, f);
		}




		else throw new IllegalArgumentException("Choix non disponible");
	}
	/**
	 * 
	 * @param n
	 * @param e
	 * @return le nombre de place souhaiter 
	 * par un etudiant pour la reservation
	 */
	public int ctlNbPlaceSouhaiter(int n, Etudiant e) {
		if(n>0 && n<=4 && e.getNivFormation()==5) return n;
		else if(n>0 && n<=2)return n;
		else {
			return -1;
		}
	}

	public int ctlNbPlaceSouhaiter(int n) {
		if(n>0 && n<3)return n;
		return -1;
	}
	/**
	 * 
	 * @param n
	 * @return le numeros de table choisit par l'etudiant
	 * et return -1 dans le cas le numero choisit ne correspond
	 * pas au table reserver aux etudiant
	 */

	public int cltnumTableEtu(int n) {
		if(n<11 || n>21) {
			return -1;
		}

		return n;
	}

	public int ctlchoixNbPLace(int n) {
		if(n<1 || n>2) {
			return -1;
		}
		return n;
	}

	public int cltnumTablePers(int n) {
		if(n<1 || n>10)return -1;
		return n;
	}
	/**
	 * 
	 * @param gala
	 * @param f
	 * @throws IOException 
	 * c'est la methode pour la sauvegarde
	 */
	public void ctlSauvergarde(Gala gala,File f) throws IOException {
		FileOutputStream fos=new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(gala);
		oos.close();
		fos.close();
	}
	/**
	 * 
	 * @param g
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * methode pour charger le gala sauvegarder
	 */
	public Gala ctlChargerGala(Gala g) throws ClassNotFoundException, IOException {

		ObjectInputStream ois;
		FileInputStream fis= new FileInputStream("gala.ser");
		ois = new ObjectInputStream(fis);
		g =(Gala) ois.readObject();
		ois.close();
		fis.close();
		return g;
	} 
	public boolean fichierExiste(File f) {
		if (f.exists())return true;
		return false;
	}
	public boolean ctlChoiximpossibled(int n,int valn,int m,int valm) {

		return false;

	}

	

}


