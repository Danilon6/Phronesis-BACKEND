## 🇬🇧 English

# Phronesis: Back-End of a Social Network for Sharing Experiences and Practical Advice

Welcome to the back-end repository of **Phronesis**, a social network dedicated to sharing experiences, reflections, and practical advice. Developed using Spring Boot and PostgreSQL, Phronesis aims to foster personal growth and emotional intelligence through community support and the exchange of life experiences.

## Project Description

Phronesis allows users to share experiences, reflections, and practical advice derived from their daily lives. The platform is designed to promote personal growth and emotional intelligence by enabling users to share both positive and negative experiences to help and inspire others. Users can share how they faced and overcame challenges, providing practical advice for similar situations. The goal is to create a supportive network that shows no one is alone in their struggles and that others have already experienced and overcome similar challenges.

## Main Features

### Home Page

- **Create Posts**: Users can create and share posts with texts and images detailing their personal experiences and reflections, including how they faced and overcame specific situations.
- **Likes**: Users can like posts from other users.
- **Favorites**: Users can add posts to their favorites for easy future reference.

### Personal Profile

- **Profile Management**: Update personal information.
- **Profile Image Upload**: Upload and update profile images.
- **CRUD on Posts and Comments**: Create, read, update, and delete personal posts and comments.
- **Report Posts**: Users can report posts that violate community guidelines.

### Admin

- **Manage Reports**: Moderate reported posts and ban users if necessary.
- **Create and Manage Announcements**: Publish and manage advertisements displayed to users randomly on the home page.

## Technologies Used

- **Backend**: Spring Boot 🍃
- **Database**: PostgreSQL 🐘
- **Language**: Java ☕️
- **Security**: Spring Security

## Project Structure

- **src/main/java**: Contains the application's source code.
- **src/main/resources**: Contains application resources, such as configuration files.
- **src/test/java**: Contains unit and integration tests for the application.

## Frontend Repository

This repository contains only the backend part of the application. You can find the frontend repository here:

👉 [Phronesis Frontend](https://github.com/Danilon6/Phronesis-FRONTEND) 👈

## Installation and Configuration

### Prerequisites

- JDK 11 or higher
- Maven 3.6.3 or higher
- PostgreSQL

### Installation Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/phronesis.git
   ```
2. Navigate to the project directory:
   ```bash
   cd phronesis
   ```
3. Configure the PostgreSQL database:
   - Create a database named `phronesis`.
   - Update the database credentials in the `src/main/resources/application.properties` file.
4. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
5. Access the application:
   Open your browser and go to `http://localhost:8080`.

## Contributing

Contributions and pull requests are welcome! Feel free to explore the open issues and contribute with improvements or bug fixes.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Contact

For questions or to report issues, you can open an issue on GitHub.

Thank you for using Phronesis! We hope this platform helps you grow personally and emotionally by sharing and learning from the experiences of others.

---

## 🇮🇹 Italiano

# Phronesis: Back-End di un Social Network per la Condivisione di Esperienze e Consigli Pratici

Benvenuti nel repository back-end di **Phronesis**, un social network dedicato alla condivisione di esperienze, riflessioni e consigli pratici. Sviluppato utilizzando Spring Boot e PostgreSQL, Phronesis mira a favorire la crescita personale e l'intelligenza emotiva attraverso il supporto della comunità e lo scambio di esperienze di vita.

## Descrizione del Progetto

Phronesis permette agli utenti di condividere esperienze, riflessioni e consigli pratici derivati dalla loro vita quotidiana. La piattaforma è pensata per favorire la crescita personale e l'intelligenza emotiva consentendo agli utenti di condividere sia esperienze positive che negative per aiutare e ispirare gli altri. Gli utenti possono condividere come hanno affrontato e superato le sfide, fornendo consigli pratici per situazioni simili. L'obiettivo è creare una rete di supporto e comprensione mostrando che nessuno è solo nelle proprie difficoltà e che altri hanno già vissuto e superato esperienze simili.

## Funzionalità Principali

### Home Page

- **Creazione Post**: Gli utenti possono creare e condividere post con testi e immagini che raccontano le loro esperienze e riflessioni personali, inclusi dettagli su come hanno affrontato e superato determinate situazioni.
- **Likes**: Possibilità di mettere "mi piace" ai post degli altri utenti.
- **Preferiti**: Aggiungere post ai preferiti per una facile consultazione futura.

### Profilo Personale

- **Gestione del Profilo**: Aggiornamento delle informazioni personali.
- **Caricamento Immagine di Profilo**: Caricare e aggiornare le immagini del profilo.
- **CRUD sui Post e Commenti**: Creare, leggere, aggiornare e cancellare i propri post e commenti.
- **Segnalazione Post**: Gli utenti possono segnalare i post che violano le linee guida della comunità.

### Admin

- **Gestione Segnalazioni**: Moderare i post segnalati e bannare gli utenti se necessario.
- **Creazione e Gestione Annunci**: Pubblicare e gestire gli annunci pubblicitari visualizzati casualmente sulla home page.

## Tecnologie Utilizzate

- **Backend**: Spring Boot 🍃
- **Database**: PostgreSQL 🐘
- **Linguaggio**: Java ☕️
- **Sicurezza**: Spring Security

## Struttura del Progetto

- **src/main/java**: Contiene il codice sorgente dell'applicazione.
- **src/main/resources**: Contiene le risorse dell'applicazione, come i file di configurazione.
- **src/test/java**: Contiene i test unitari e di integrazione per l'applicazione.

## Frontend Repository

Questo repository contiene solo la parte backend dell'applicazione. Puoi trovare il repository del frontend qui:

👉 [Phronesis Frontend](https://github.com/Danilon6/Phronesis-FRONTEND) 👈

## Installazione e Configurazione

### Prerequisiti

- JDK 11 o superiore
- Maven 3.6.3 o superiore
- PostgreSQL

### Passaggi per l'Installazione

1. Clona il repository:
   ```bash
   git clone https://github.com/your-username/phronesis.git
   ```
2. Naviga nella directory del progetto:
   ```bash
   cd phronesis
   ```
3. Configura il database PostgreSQL:
   - Crea un database denominato `phronesis`.
   - Aggiorna le credenziali del database nel file `src/main/resources/application.properties`.
4. Compila e avvia l'applicazione:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
5. Accedi all'applicazione:
   Apri il browser e vai all'indirizzo `http://localhost:8080`.

## Contribuire

Contributi e pull request sono benvenuti! Sentiti libero di esplorare le issue aperte e contribuire con miglioramenti o correzioni di bug.

1. Fork del progetto
2. Crea il tuo branch di feature (`git checkout -b feature/AmazingFeature`)
3. Commit delle modifiche (`git commit -m 'Add some AmazingFeature'`)
4. Push al branch (`git push origin feature/AmazingFeature`)
5. Apri una Pull Request

## Contatti

Per domande o segnalazioni di problemi, puoi aprire un'issue su GitHub.

Grazie per aver utilizzato Phronesis! Speriamo che questa piattaforma ti aiuti a crescere personalmente ed emotivamente condividendo e imparando dalle esperienze degli altri.
