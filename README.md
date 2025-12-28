# BrandTube — Creator–Brand Sponsorship Platform (In Progress)

BrandTube is a creator–brand sponsorship platform designed to streamline collaboration between content creators and businesses.  
The platform enables creators to showcase their channels and sponsorship history, while allowing brands to discover creators, send sponsorship offers, and manage collaboration lifecycles through structured proposals and dashboards.

> **Status:** This project is actively under development. Features, schemas, and workflows are evolving.

## Core Concept

BrandTube operates as a two-sided platform:
- **Creators** manage profiles, channels, and sponsorship proposals
- **Brands** discover creators, initiate offers, and manage collaborations
- **Proposals/Offers** follow a defined lifecycle from draft to completion and approval


## Entities

### Creator

Creators represent individual content creators on the platform.

**Attributes & Features**
- Name
- One-line description
- About section
- Profile picture & banner
- Channel list (e.g., YouTube)
- Aggregated channel statistics
- Sponsorship insights:
  - Ongoing sponsorships
  - Completed sponsorship count
  - Sponsorship timeline
- Proposal management:
  - Draft proposals
  - Sent proposals
- Currency:
  - Creators receive payments in their own local currency


### Brand / Business

Brands represent businesses seeking collaborations with creators.

**Attributes & Features**
- Name
- Profile picture & banner
- Sponsorship insights:
  - Total sponsorship count
  - Sponsorship timeline
- Active sponsorships dashboard
- Ability to initiate sponsorship offers


### Proposal / Offer

Proposals define collaboration agreements between creators and brands.  
The same entity is used regardless of who initiates the proposal.

#### Lifecycle

Initial stages differ only by initiator:
- `DRAFT`
- `SENT`
- `REJECTED`

Once accepted:
- `ACCEPTED` → `IN_PROGRESS`
- Creator marks as `COMPLETED`
- Brand marks as `APPROVED`

Terminal states:
- `CANCELED`


### Proposal Details

Each proposal includes:
- Creator
- Brand / Business
- Initiated by (CREATOR or BRAND)
- Status  
  (`DRAFT`, `SENT`, `ACCEPTED`, `REJECTED`, `IN_PROGRESS`, `COMPLETED`, `APPROVED`, `CANCELED`)
- Publish window:
  - Start date
  - End date
- Content requirements:
  - Integrated video count
  - Dedicated video count
  - Shorts count
  - Description link included (yes/no)
- Commercial details:
  - Amount
  - Currency (creator’s currency)
- Metadata:
  - Title
  - Description
  - Guidelines
  - Created time
  - Updated time


## Tech Stack

- **Backend:** Java, Spring Boot, Spring Security, JWT
- **Frontend:** React
- **Database:** MySQL
- **Deployment:** Docker-based setup