<h1 align="center">📧 Asynchronous Email</h1>

<p align="center">
  Projeto de estudo e portfólio demonstrando uma arquitetura de microsserviços orientada a eventos,
  com envio de e-mails de forma assíncrona usando Apache Kafka.
</p>

<hr/>

<h2>📌 Visão Geral</h2>

<p>
  O <strong>Asynchronous Email</strong> é um projeto de <strong>estudo e portfólio</strong> que demonstra
  como implementar uma arquitetura de <strong>microsserviços orientada a eventos</strong>, onde o envio
  de e-mails ocorre de forma <strong>assíncrona</strong>.
</p>

<p>
  O objetivo principal é mostrar como desacoplar responsabilidades entre serviços, garantindo
  <strong>escalabilidade</strong>, <strong>resiliência</strong> e uma melhor <strong>experiência do usuário</strong>,
  seguindo práticas amplamente utilizadas em sistemas reais.
</p>

<p>
  Neste sistema, o envio de e-mails <strong>não depende de requisições HTTP diretas</strong>.
  O microsserviço de e-mail apenas <strong>escuta eventos publicados em um tópico Kafka</strong> e reage
  a eles quando um novo usuário é cadastrado.
</p>

<hr/>

<h2>🎯 O que o projeto faz</h2>

<ol>
  <li>Um usuário é cadastrado no <strong>user-service</strong></li>
  <li>Após o cadastro, o serviço publica um evento informando que um novo usuário foi criado</li>
  <li>O <strong>email-service</strong> consome esse evento a partir de um tópico Kafka</li>
  <li>O <strong>email-service</strong> envia automaticamente um e-mail (ex: boas-vindas)</li>
</ol>

<p><strong>Pontos importantes:</strong></p>

<ul>
  <li>O <strong>user-service</strong> não sabe como o e-mail é enviado</li>
  <li>O <strong>email-service</strong> não expõe APIs REST</li>
  <li>A comunicação ocorre exclusivamente por <strong>eventos</strong></li>
</ul>

<hr/>

<h2>🏗️ Arquitetura</h2>

<p>
  O projeto segue uma <strong>arquitetura de microsserviços orientada a eventos</strong>,
  utilizando o <strong>Apache Kafka</strong> como sistema de mensageria.
</p>

<pre>
┌──────────────────────────┐
│      user-service        │
│                          │
│ - Cadastro de usuários   │
│ - Publica evento Kafka   │
│   (UserCreatedEvent)     │
└─────────────┬────────────┘
              │
              ▼
        ┌───────────────┐
        │     Kafka     │
        │  (tópico)     │
        └───────┬───────┘
                │
                ▼
┌──────────────────────────┐
│      email-service       │
│                          │
│ - Escuta tópico Kafka    │
│ - Consome eventos        │
│ - Envia e-mail           │
│ - Não expõe APIs REST    │
└──────────────────────────┘
</pre>

<h3>📌 Características da arquitetura</h3>

<ul>
  <li>Comunicação <strong>assíncrona</strong></li>
  <li>Serviços <strong>desacoplados</strong></li>
  <li>Alta <strong>escalabilidade</strong></li>
  <li>Fácil extensão para novos consumidores</li>
</ul>

<hr/>

<h2>🔔 Por que o email-service não recebe requisições?</h2>

<p>
  Em sistemas reais, o envio de e-mails é uma tarefa potencialmente lenta e sujeita a falhas
  (SMTP, serviços externos, timeout).
</p>

<p>
  Ao invés de realizar esse envio de forma síncrona, este projeto adota uma abordagem mais robusta:
</p>

<ul>
  <li>✅ Cadastro de usuário rápido</li>
  <li>✅ Envio de e-mail em segundo plano</li>
  <li>✅ Falhas no e-mail não afetam o fluxo principal</li>
</ul>

<p>
  Esse modelo é amplamente utilizado em sistemas distribuídos reais,
  como plataformas de e-commerce, fintechs e aplicações de grande escala.
</p>

<hr/>

<h2>🛠️ Tecnologias utilizadas e seus motivos</h2>

<h3>☕ Java + Spring Boot</h3>
<ul>
  <li>Padrão consolidado no mercado</li>
  <li>Excelente suporte a microsserviços</li>
  <li>Integração nativa com Kafka e Docker</li>
</ul>

<p><em>No mundo real:</em> muito utilizado em sistemas bancários, SaaS e aplicações corporativas.</p>

<h3>📨 Apache Kafka</h3>
<ul>
  <li>Arquitetura orientada a eventos</li>
  <li>Alta performance e escalabilidade</li>
  <li>Permite múltiplos consumidores</li>
</ul>

<p><em>No mundo real:</em> usado por empresas como Netflix, Uber, LinkedIn e bancos.</p>

<h3>🐳 Docker & Docker Compose</h3>
<ul>
  <li>Isolamento de serviços</li>
  <li>Facilidade de setup local</li>
  <li>Ambiente próximo ao de produção</li>
</ul>

<h3>🗄️ PostgreSQL</h3>
<ul>
  <li>Persistência de dados confiável</li>
  <li>Banco relacional robusto</li>
</ul>

<hr/>

<h2>💼 Relevância para portfólio</h2>

<p>Este projeto demonstra conhecimento prático em:</p>

<ul>
  <li>Microsserviços</li>
  <li>Arquitetura orientada a eventos</li>
  <li>Apache Kafka</li>
  <li>Docker</li>
  <li>Boas práticas de backend moderno</li>
</ul>

<p>
  Ele vai além de CRUDs simples e reflete decisões arquiteturais reais,
  mostrando preocupação com <strong>escalabilidade</strong>,
  <strong>resiliência</strong> e <strong>manutenibilidade</strong>.
</p>

<hr/>

<h2>🚀 Possíveis evoluções</h2>

<ul>
  <li>Retry e Dead Letter Queue (DLQ)</li>
  <li>Observabilidade (logs e métricas)</li>
  <li>Autenticação e autorização</li>
  <li>Novos consumidores de eventos</li>
  <li>Deploy em cloud</li>
</ul>
