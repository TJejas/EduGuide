package com.example.eduselect.model

// Draft question text, written by us. Not yet reviewed by the lead.
object QuestionBank {

    const val QUESTIONS_PER_DOMAIN = 20
    const val QUESTIONS_PER_BRANCH = 5

    private val questionTexts: Map<Domain, List<String>> = mapOf(
        Domain.FILMMAKING to listOf(
            "Would you enjoy writing a story and turning it into a short film?",
            "When you watch a movie, do you notice the camera angles, lighting and editing?",
            "Would you enjoy shooting and editing videos on your phone?",
            "Would you like to direct actors and tell them how to play a scene?",
            "Are you curious about how visual effects in movies are made?",
            "Would you enjoy writing dialogue for characters in a film?",
            "Would you like to plan each shot of a scene as drawings (a storyboard)?",
            "Do you enjoy watching films in other languages to see how they tell stories?",
            "Would you enjoy choosing music and sound effects to set the mood of a scene?",
            "Would you like to work on a film set, even with long and irregular hours?",
            "Would you enjoy cutting and arranging video clips so a story flows well?",
            "Are you interested in how a film is planned, budgeted and scheduled?",
            "Would you enjoy designing costumes, sets or locations for a film?",
            "Do you like discussing why a film worked or did not work?",
            "Would you enjoy making documentaries about real people and places?",
            "Would you like to learn how professional film cameras and lenses work?",
            "Would you enjoy acting in a short film or play?",
            "Would you like to make an animated film?",
            "Would you enjoy entering your own short film in a festival or online contest?",
            "Would you like to study filmmaking at a film institute after Class 12?"
        ),
        Domain.HOTEL_MANAGEMENT to listOf(
            "Would you enjoy welcoming guests and making them feel comfortable?",
            "Do you like cooking or trying out new recipes?",
            "Would you enjoy planning an event like a wedding reception or a conference?",
            "Would you like to work in a hotel, resort or cruise ship?",
            "Are you curious about how a big hotel kitchen serves hundreds of people?",
            "Would you enjoy calmly solving a guest's problem, even if they are upset?",
            "Would you like to learn about food from different states and countries?",
            "Would you enjoy decorating a room or dining table to look attractive?",
            "Do you like keeping things clean, organised and well arranged?",
            "Would you enjoy managing a team of staff during a busy shift?",
            "Would you like to run your own restaurant, café or homestay one day?",
            "Would you enjoy working in tourism and showing visitors around your region?",
            "Are you interested in how hotels set prices and attract customers?",
            "Would you be happy working on weekends and festivals when others are on holiday?",
            "Would you like to learn baking and pastry making?",
            "Would you enjoy talking with people from different cultures and backgrounds?",
            "Would you like to work at an airport, airline or travel company?",
            "Would you enjoy checking food quality and hygiene standards?",
            "Would you like to handle bookings, billing and front desk work?",
            "Would you enjoy a job where you are on your feet and moving all day?"
        ),
        Domain.DEFENCE to listOf(
            "Would you like to serve the country in the armed forces?",
            "Do you enjoy physical activities like running, sports or exercise?",
            "Would you be comfortable following a strict daily routine and discipline?",
            "Would you like to wear a uniform and hold a rank?",
            "Would you enjoy living away from home, even in remote places?",
            "Do you stay calm and think clearly in difficult or risky situations?",
            "Would you enjoy leading a group and taking responsibility for them?",
            "Are you interested in military history and famous battles?",
            "Would you like to join NCC or a similar cadet programme?",
            "Would you enjoy outdoor training like trekking, camping or swimming?",
            "Would you like to help people during floods, earthquakes or other disasters?",
            "Are you interested in how fighter jets, warships or tanks work?",
            "Would you be willing to move to a new place every few years for work?",
            "Would you like to prepare for exams like NDA or CDS?",
            "Do you enjoy working as part of a team toward a shared goal?",
            "Would you enjoy learning to use maps, radios and navigation tools?",
            "Do you follow news about the Indian Army, Navy or Air Force?",
            "Would you take on a job with some personal risk in order to protect others?",
            "Would you choose a career that values courage and loyalty over a high salary?",
            "Would you like to work in security, intelligence or police services?"
        ),
        Domain.MASS_COMMUNICATION to listOf(
            "Would you enjoy writing news articles or blog posts?",
            "Do you like speaking in front of an audience?",
            "Would you like to be a news anchor or radio jockey?",
            "Would you enjoy interviewing people and asking them questions?",
            "Do you follow the news and like discussing current events?",
            "Would you enjoy creating posts or videos for social media?",
            "Would you like to work in advertising and create campaigns for brands?",
            "Would you enjoy checking whether a news story or viral message is true?",
            "Would you like to host a podcast or YouTube channel?",
            "Would you enjoy writing in more than one language, like English and your mother tongue?",
            "Would you like to work in public relations, managing how people see a company?",
            "Would you enjoy reporting from the field, at events or on the street?",
            "Do you enjoy reading newspapers, magazines or long articles?",
            "Would you like to design posters, layouts or graphics that carry a message?",
            "Would you enjoy writing scripts for TV shows or radio programmes?",
            "Are you curious about how news channels decide which stories to show?",
            "Would you enjoy taking photographs that tell a story?",
            "Would you like to work on campaigns that spread awareness about social issues?",
            "Would you enjoy editing other people's writing to make it clearer?",
            "Would you like to study journalism or mass communication after Class 12?"
        )
    )

    private val followUpTexts: Map<DefenceBranch, List<String>> = mapOf(
        DefenceBranch.AIR_FORCE to listOf(
            "Would you like to fly an aircraft?",
            "Are you fascinated by planes, rockets or space?",
            "Would you enjoy working at an air base with fighter jets and helicopters?",
            "Would you like to work as an air traffic controller or radar operator?",
            "Do heights and speed excite you rather than scare you?"
        ),
        DefenceBranch.NAVY to listOf(
            "Would you enjoy spending weeks at sea on a ship?",
            "Do you like swimming or being near the ocean?",
            "Would you like to serve on a warship or submarine?",
            "Are you interested in navigating using maps, stars and the sea?",
            "Would you enjoy protecting India's coastline and ports?"
        ),
        DefenceBranch.ARMY to listOf(
            "Would you enjoy training on land, in jungles, deserts or mountains?",
            "Would you like to lead soldiers in the field?",
            "Are you interested in tanks, artillery or building bridges and roads?",
            "Would you be willing to serve in border areas like Kashmir or the North-East?",
            "Would you like to work closely with local people during rescue and relief work?"
        )
    )

    // ids: Filmmaking 1-20, Hotel Management 21-40, Defence 41-60, Mass Communication 61-80
    val questions: List<Question> = Domain.entries.flatMap { domain ->
        questionTexts.getValue(domain).mapIndexed { i, text ->
            Question(
                id = domain.ordinal * QUESTIONS_PER_DOMAIN + i + 1,
                domain = domain,
                text = text
            )
        }
    }

    // ids: Air Force 1-5, Navy 6-10, Army 11-15
    val defenceFollowUpQuestions: List<DefenceFollowUpQuestion> = DefenceBranch.entries.flatMap { branch ->
        followUpTexts.getValue(branch).mapIndexed { i, text ->
            DefenceFollowUpQuestion(
                id = branch.ordinal * QUESTIONS_PER_BRANCH + i + 1,
                branch = branch,
                text = text
            )
        }
    }
}
