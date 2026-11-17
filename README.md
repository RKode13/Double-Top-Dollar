# Double Dollar Deal

An original 3-reel, 5-line classic slot game. Play money only: no real money and no prizes.

## Play it
- **Android app:**
  1. Open this repository on GitHub, then go to **Releases → latest**.
  2. Download `DoubleDollarDeal.apk`, open it, and tap **Install**.
  3. The first time, Android asks you to allow "Install unknown apps" for your browser.
  - Every push to GitHub builds a new version. A new version installs over the old one and keeps your balance.
- **On a computer:** download `index.html` and open it in Chrome or Edge.
- **In a browser:** https://rkode13.github.io/Double-Top-Dollar/ (published automatically on every push once GitHub Pages is turned on: the repository must be public, then Settings → Pages → Source: **GitHub Actions**).

## Features
- 3 reels, 3 rows and 5 paylines, with cherries, bars, 7s and a **2X wild** (one doubles a win, two make it ×4).
- **Deal Round:** DEAL anywhere on reel 3 awards 3–5 offers from the money board. The 2X diamonds can double or quadruple an offer.
  - Take the offer or try again. A "Best play says" hint shows the better choice.
- **Settings:** bet ($0.10–$30), auto spin (10–50), and switches for all sound, music, sound effects and voice.
- Chasing marquee lights, synthesized sounds and music, and a spoken announcer.

## Odds
9 lines, pays in multiples of the total bet: three 2X coins 80×, three 7s 8×, triple bars 4×, double bars 2.5×, single bars 1×, any bars 0.5×, cherries 1× / 0.5× / 0.2×.
- Deal Round about 1 in 39 spins, always 4 offers.
- Payback 94.97% following best play (take the 1st offer above about 15.3× the bet, the 2nd above 13.8×, the 3rd above 11.6×).
- The info tab shows every figure exactly, calculated from the reel weights.

## Artwork
The images in `art/` were made by the owner with an AI image generator. Their painted checkerboard backgrounds were removed, and they were saved as transparent WebP files.
- The cabinet and the reel frame are drawn as stretchable frames (CSS `border-image`).
- The cabinet's painted light bulbs are animated with glowing dots placed on top of them.

## Announcer voice
`art/voice/` holds 22 announcer clips the owner recorded with ElevenLabs.
- Choose Announcer, Phone voice or Off in Settings → 🔊.
- With Phone voice, the device's own speech says the lines, including dollar amounts.
