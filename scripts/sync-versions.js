import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url';
import packageJson from '../package.json' assert { type: 'json' };

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

// Get version from package.json
const version = packageJson.version;

// Update gradle.properties
const gradlePropertiesPath = path.join(__dirname, '..', 'gradle.properties');
let gradleProperties = fs.readFileSync(gradlePropertiesPath, 'utf8');

// Replace versionName line
gradleProperties = gradleProperties.replace(
  /^versionName=.*/m,
  `versionName=${version}`
);

fs.writeFileSync(gradlePropertiesPath, gradleProperties);

console.log(`✅ Synced version ${version} to gradle.properties`);