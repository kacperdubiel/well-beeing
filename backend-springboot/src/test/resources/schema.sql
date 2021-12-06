CREATE TABLE IF NOT EXISTS public.activity_goal
(
    activity_goal_id
    bigint
    NOT
    NULL,
    goal_creation_date
    timestamp
    without
    time
    zone
    NOT
    NULL,
    goal_target_date
    timestamp
    without
    time
    zone,
    goal_type
    character
    varying
(
    255
) NOT NULL,
    numeric_value double precision,
    text_value character varying
(
    255
),
    owner uuid
    );


ALTER TABLE public.activity_goal
    OWNER TO wellbeeing;

--
-- Name: activity_goal_activity_goal_id_seq; Type: SEQUENCE; Schema: public; Owner: wellbeeing
--

CREATE SEQUENCE IF NOT EXISTS public.activity_goal_activity_goal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER TABLE public.activity_goal_activity_goal_id_seq
    OWNER TO wellbeeing;

--
-- Name: activity_goal_activity_goal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wellbeeing
--

ALTER SEQUENCE public.activity_goal_activity_goal_id_seq OWNED BY public.activity_goal.activity_goal_id;


--
-- Name: ailment; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.ailment
(
    id
    uuid
    NOT
    NULL,
    change_in_calories
    integer,
    change_in_proteins_gram_per_kilogram
    integer,
    description
    character
    varying
(
    255
),
    is_low_glycemic_index_recommended boolean,
    name character varying
(
    255
),
    type character varying
(
    255
)
    );


ALTER TABLE public.ailment
    OWNER TO wellbeeing;

--
-- Name: ailment_nutrition_label; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.ailment_nutrition_label
(
    nutrition_label_id
    uuid
    NOT
    NULL,
    ailment_id
    uuid
    NOT
    NULL
);


ALTER TABLE public.ailment_nutrition_label
    OWNER TO wellbeeing;

--
-- Name: ailment_sport_label; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.ailment_sport_label
(
    ailment_id
    uuid
    NOT
    NULL,
    sport_label_id
    bigint
    NOT
    NULL
);


ALTER TABLE public.ailment_sport_label
    OWNER TO wellbeeing;

--
-- Name: comment; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.comment
(
    comment_id
    bigint
    NOT
    NULL,
    added_date
    timestamp
    without
    time
    zone,
    com_content
    character
    varying
(
    255
),
    commenter_id uuid,
    post_id bigint,
    is_deleted boolean
    );


ALTER TABLE public.comment
    OWNER TO wellbeeing;

--
-- Name: comment_comment_id_seq; Type: SEQUENCE; Schema: public; Owner: wellbeeing
--

CREATE SEQUENCE IF NOT EXISTS public.comment_comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER TABLE public.comment_comment_id_seq
    OWNER TO wellbeeing;

--
-- Name: comment_comment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wellbeeing
--

ALTER SEQUENCE public.comment_comment_id_seq OWNED BY public.comment.comment_id;


--
-- Name: conversations; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.conversations
(
    id
    uuid
    NOT
    NULL,
    connection_type
    integer
    NOT
    NULL,
    is_read_by_first_user
    boolean,
    is_read_by_second_user
    boolean,
    last_message_date
    timestamp
    without
    time
    zone,
    first_profile_profile_user_id
    uuid,
    second_profile_profile_user_id
    uuid
);


ALTER TABLE public.conversations
    OWNER TO wellbeeing;

--
-- Name: diet; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.diet
(
    id
    uuid
    NOT
    NULL,
    connected_with_macros
    boolean,
    description
    character
    varying
(
    255
),
    name character varying
(
    255
)
    );


ALTER TABLE public.diet
    OWNER TO wellbeeing;

--
-- Name: diet_calc_meal_suggestions; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.diet_calc_meal_suggestions
(
    id
    uuid
    NOT
    NULL,
    glycemic_index_level
    integer,
    meal_type
    character
    varying
(
    255
),
    number_of_calories double precision,
    diet_calculation_id uuid
    );


ALTER TABLE public.diet_calc_meal_suggestions
    OWNER TO wellbeeing;

--
-- Name: diet_nutrition_label; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.diet_nutrition_label
(
    nutrition_label_id
    uuid
    NOT
    NULL,
    diet_id
    uuid
    NOT
    NULL
);


ALTER TABLE public.diet_nutrition_label
    OWNER TO wellbeeing;

--
-- Name: dietician_profile; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.dietician_profile
(
    user_id uuid NOT NULL
);


ALTER TABLE public.dietician_profile
    OWNER TO wellbeeing;

--
-- Name: dish; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.dish
(
    id
    uuid
    NOT
    NULL,
    active
    boolean,
    created_date
    date,
    derived_caffeine
    double
    precision,
    derived_calories
    double
    precision,
    derived_carbohydrates
    double
    precision,
    derived_cholesterol
    double
    precision,
    derived_fats
    double
    precision,
    derived_fiber
    double
    precision,
    derived_proteins
    double
    precision,
    derived_salt
    double
    precision,
    derived_saturated_fats
    double
    precision,
    derived_sugar
    double
    precision,
    description
    character
    varying
(
    255
),
    draft boolean,
    glycemic_index_level character varying
(
    255
),
    img_dish_path character varying
(
    255
),
    name character varying
(
    255
),
    recipe character varying
(
    1000
),
    creator_id uuid
    );


ALTER TABLE public.dish
    OWNER TO wellbeeing;

--
-- Name: dish_meal_type; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.dish_meal_type
(
    id
    uuid
    NOT
    NULL,
    meal_type
    character
    varying
(
    255
),
    dish_id uuid
    );


ALTER TABLE public.dish_meal_type
    OWNER TO wellbeeing;

--
-- Name: dish_nutrition_label; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.dish_nutrition_label
(
    dish_id
    uuid
    NOT
    NULL,
    nutrition_label_id
    uuid
    NOT
    NULL
);


ALTER TABLE public.dish_nutrition_label
    OWNER TO wellbeeing;

--
-- Name: dish_product_detail; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.dish_product_detail
(
    id
    uuid
    NOT
    NULL,
    amount_of_product
    double
    precision,
    measure_type
    character
    varying
(
    255
),
    derived_caffeine double precision,
    derived_calories double precision,
    derived_carbohydrates double precision,
    derived_cholesterol double precision,
    derived_fats double precision,
    derived_fiber double precision,
    derived_proteins double precision,
    derived_salt double precision,
    derived_saturated_fats double precision,
    derived_sugar double precision,
    product_id uuid,
    dish_id uuid
    );


ALTER TABLE public.dish_product_detail
    OWNER TO wellbeeing;

--
-- Name: doctor_profile; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.doctor_profile
(
    user_id uuid NOT NULL
);


ALTER TABLE public.doctor_profile
    OWNER TO wellbeeing;

--
-- Name: doctor_specialization; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.doctor_specialization
(
    id
    uuid
    NOT
    NULL,
    name
    character
    varying
(
    255
) NOT NULL
    );


ALTER TABLE public.doctor_specialization
    OWNER TO wellbeeing;

--
-- Name: doctors_specializations; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.doctors_specializations
(
    user_id
    uuid
    NOT
    NULL,
    specialization_id
    uuid
    NOT
    NULL
);


ALTER TABLE public.doctors_specializations
    OWNER TO wellbeeing;

--
-- Name: exercise; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.exercise
(
    exercise_id
    bigint
    NOT
    NULL,
    description
    text,
    type
    character
    varying
(
    255
) NOT NULL,
    instruction text,
    is_private boolean,
    metabolic_eqv_of_task double precision,
    name character varying
(
    255
) NOT NULL,
    creator uuid,
    path_to_video_instruction character varying
(
    255
),
    is_deleted boolean
    );


ALTER TABLE public.exercise
    OWNER TO wellbeeing;

--
-- Name: exercise_exercise_id_seq; Type: SEQUENCE; Schema: public; Owner: wellbeeing
--

CREATE SEQUENCE IF NOT EXISTS public.exercise_exercise_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER TABLE public.exercise_exercise_id_seq
    OWNER TO wellbeeing;

--
-- Name: exercise_exercise_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wellbeeing
--

ALTER SEQUENCE public.exercise_exercise_id_seq OWNED BY public.exercise.exercise_id;


--
-- Name: exercise_in_training; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.exercise_in_training
(
    id
    bigint
    NOT
    NULL,
    reps
    integer,
    series
    integer,
    time_in_seconds
    integer,
    exercise_id
    bigint
    NOT
    NULL,
    training_id
    bigint
    NOT
    NULL
);


ALTER TABLE public.exercise_in_training
    OWNER TO wellbeeing;

--
-- Name: exercise_in_training_id_seq; Type: SEQUENCE; Schema: public; Owner: wellbeeing
--

CREATE SEQUENCE IF NOT EXISTS public.exercise_in_training_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER TABLE public.exercise_in_training_id_seq
    OWNER TO wellbeeing;

--
-- Name: exercise_in_training_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wellbeeing
--

ALTER SEQUENCE public.exercise_in_training_id_seq OWNED BY public.exercise_in_training.id;


--
-- Name: exercise_labels; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.exercise_labels
(
    exercise_id
    bigint
    NOT
    NULL,
    sport_label_id
    bigint
    NOT
    NULL
);


ALTER TABLE public.exercise_labels
    OWNER TO wellbeeing;

--
-- Name: follow; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.follow
(
    follow_id
    bigint
    NOT
    NULL,
    added_date
    timestamp
    without
    time
    zone,
    followed_id
    uuid,
    follower_id
    uuid
);


ALTER TABLE public.follow
    OWNER TO wellbeeing;

--
-- Name: follow_follow_id_seq; Type: SEQUENCE; Schema: public; Owner: wellbeeing
--

CREATE SEQUENCE IF NOT EXISTS public.follow_follow_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER TABLE public.follow_follow_id_seq
    OWNER TO wellbeeing;

--
-- Name: follow_follow_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wellbeeing
--

ALTER SEQUENCE public.follow_follow_id_seq OWNED BY public.follow.follow_id;


--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: wellbeeing
--

CREATE SEQUENCE IF NOT EXISTS public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER TABLE public.hibernate_sequence
    OWNER TO wellbeeing;

--
-- Name: likes; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.likes
(
    like_id
    bigint
    NOT
    NULL,
    added_date
    timestamp
    without
    time
    zone,
    liker_id
    uuid,
    post_id
    bigint
);


ALTER TABLE public.likes
    OWNER TO wellbeeing;

--
-- Name: likes_like_id_seq; Type: SEQUENCE; Schema: public; Owner: wellbeeing
--

CREATE SEQUENCE IF NOT EXISTS public.likes_like_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER TABLE public.likes_like_id_seq
    OWNER TO wellbeeing;

--
-- Name: likes_like_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wellbeeing
--

ALTER SEQUENCE public.likes_like_id_seq OWNED BY public.likes.like_id;


--
-- Name: measure_types; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.measure_types
(
    id
    uuid
    NOT
    NULL,
    max_value
    double
    precision
    NOT
    NULL,
    min_value
    double
    precision
    NOT
    NULL,
    name
    character
    varying
(
    255
) NOT NULL,
    unit character varying
(
    255
),
    "precision" integer
    );


ALTER TABLE public.measure_types
    OWNER TO wellbeeing;

--
-- Name: measures; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.measures
(
    id
    uuid
    NOT
    NULL,
    measure_date
    timestamp
    without
    time
    zone
    NOT
    NULL,
    value
    double
    precision
    NOT
    NULL,
    measure_type_id
    uuid,
    owner_profile_user_id
    uuid
);


ALTER TABLE public.measures
    OWNER TO wellbeeing;

--
-- Name: messages; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.messages
(
    id
    uuid
    NOT
    NULL,
    create_date
    timestamp
    without
    time
    zone,
    data
    character
    varying
(
    255
) NOT NULL,
    message_type integer NOT NULL,
    conversation_id uuid,
    sender_profile_user_id uuid
    );


ALTER TABLE public.messages
    OWNER TO wellbeeing;

--
-- Name: nutrition_label; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.nutrition_label
(
    nutrition_label_id
    uuid
    NOT
    NULL,
    active
    boolean,
    description
    character
    varying
(
    255
),
    name character varying
(
    255
),
    creator_user_id uuid
    );


ALTER TABLE public.nutrition_label
    OWNER TO wellbeeing;

--
-- Name: nutrition_plan; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.nutrition_plan
(
    id
    uuid
    NOT
    NULL,
    generation_date
    timestamp
    without
    time
    zone,
    is_main
    boolean,
    name
    character
    varying
(
    255
),
    diet_id uuid,
    profile_id uuid,
    main boolean,
    owner_id uuid,
    suggested_id uuid
    );


ALTER TABLE public.nutrition_plan
    OWNER TO wellbeeing;

--
-- Name: nutrition_plan_position; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.nutrition_plan_position
(
    id
    uuid
    NOT
    NULL,
    meal_type
    character
    varying
(
    255
),
    week_day character varying
(
    255
),
    dish_id uuid,
    nutrition_plan_id uuid
    );


ALTER TABLE public.nutrition_plan_position
    OWNER TO wellbeeing;

--
-- Name: opinion; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.opinion
(
    opinion_id
    bigint
    NOT
    NULL,
    opinion_content
    character
    varying
(
    255
),
    rating integer,
    giver_id uuid,
    receiver_id uuid,
    added_date timestamp without time zone,
    is_deleted boolean
    );


ALTER TABLE public.opinion
    OWNER TO wellbeeing;

--
-- Name: opinion_opinion_id_seq; Type: SEQUENCE; Schema: public; Owner: wellbeeing
--

CREATE SEQUENCE IF NOT EXISTS public.opinion_opinion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER TABLE public.opinion_opinion_id_seq
    OWNER TO wellbeeing;

--
-- Name: opinion_opinion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wellbeeing
--

ALTER SEQUENCE public.opinion_opinion_id_seq OWNED BY public.opinion.opinion_id;


--
-- Name: post; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.post
(
    post_id
    bigint
    NOT
    NULL,
    added_date
    timestamp
    without
    time
    zone,
    post_content
    character
    varying
(
    255
),
    post_img_path character varying
(
    255
),
    priv_settings character varying
(
    255
),
    creator_id uuid,
    is_deleted boolean,
    is_sharing boolean,
    original_post_id bigint,
    sharing_counter integer
    );


ALTER TABLE public.post
    OWNER TO wellbeeing;

--
-- Name: post_post_id_seq; Type: SEQUENCE; Schema: public; Owner: wellbeeing
--

CREATE SEQUENCE IF NOT EXISTS public.post_post_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER TABLE public.post_post_id_seq
    OWNER TO wellbeeing;

--
-- Name: post_post_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wellbeeing
--

ALTER SEQUENCE public.post_post_id_seq OWNED BY public.post.post_id;


--
-- Name: product; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.product
(
    id
    uuid
    NOT
    NULL,
    caffeine_per_hundred_grams
    double
    precision,
    calories_per_hundred_grams
    double
    precision,
    carbohydrates_per_hundred_grams
    double
    precision,
    cholesterol_per_hundred_grams
    double
    precision,
    fats_per_hundred_grams
    double
    precision,
    fiber_per_hundred_grams
    double
    precision,
    glycemic_index_level
    character
    varying
(
    255
),
    name character varying
(
    255
),
    proteins_per_hundred_grams double precision,
    salt_per_hundred_grams double precision,
    saturated_fats_per_hundred_grams double precision,
    sugars_per_hundred_grams double precision
    );


ALTER TABLE public.product
    OWNER TO wellbeeing;

--
-- Name: product_macro_detail; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.product_macro_detail
(
    id
    uuid
    NOT
    NULL,
    amount_of_element_per_hundred_grams
    double
    precision,
    measure_type
    character
    varying
(
    255
),
    detailed_macro_type character varying
(
    255
),
    product_id uuid
    );


ALTER TABLE public.product_macro_detail
    OWNER TO wellbeeing;

--
-- Name: product_mineral_detail; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.product_mineral_detail
(
    id
    uuid
    NOT
    NULL,
    amount_of_element_per_hundred_grams
    double
    precision,
    measure_type
    character
    varying
(
    255
),
    mineral_type character varying
(
    255
),
    product_id uuid
    );


ALTER TABLE public.product_mineral_detail
    OWNER TO wellbeeing;

--
-- Name: product_vitamin_detail; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.product_vitamin_detail
(
    id
    uuid
    NOT
    NULL,
    amount_of_element_per_hundred_grams
    double
    precision,
    measure_type
    character
    varying
(
    255
),
    vitamin_type character varying
(
    255
),
    product_id uuid
    );


ALTER TABLE public.product_vitamin_detail
    OWNER TO wellbeeing;

--
-- Name: profile; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.profile
(
    esex character varying
(
    255
),
    birthday timestamp without time zone,
    description character varying
(
    255
),
    e_nutrition_tag character varying
(
    255
),
    e_sport_tag character varying
(
    255
),
    first_name character varying
(
    255
),
    last_name character varying
(
    255
),
    profile_img_path character varying
(
    255
),
    profile_user_id uuid NOT NULL,
    profile_card_id uuid,
    last_request_time timestamp
                       without time zone
    );


ALTER TABLE public.profile
    OWNER TO wellbeeing;

--
-- Name: profile_card; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.profile_card
(
    id
    uuid
    NOT
    NULL,
    esex
    character
    varying
(
    255
),
    activity_level character varying
(
    255
),
    age integer,
    diet_goal character varying
(
    255
),
    height double precision,
    is_vegan boolean,
    is_vegetarian boolean,
    training_activity_time_per_week integer,
    weight double precision,
    diet_calculations_id uuid
    );


ALTER TABLE public.profile_card
    OWNER TO wellbeeing;

--
-- Name: profile_card_ailment; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.profile_card_ailment
(
    ailment_id
    uuid
    NOT
    NULL,
    profile_card_id
    uuid
    NOT
    NULL
);


ALTER TABLE public.profile_card_ailment
    OWNER TO wellbeeing;

--
-- Name: profile_card_disliked_product; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.profile_card_disliked_product
(
    disliked_product_id
    uuid
    NOT
    NULL,
    profile_card_id
    uuid
    NOT
    NULL
);


ALTER TABLE public.profile_card_disliked_product
    OWNER TO wellbeeing;

--
-- Name: profile_connections; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.profile_connections
(
    id
    uuid
    NOT
    NULL,
    accept_date
    timestamp
    without
    time
    zone,
    connection_type
    integer
    NOT
    NULL,
    is_accepted
    boolean,
    request_date
    timestamp
    without
    time
    zone,
    connected_with_profile_user_id
    uuid,
    profile_profile_user_id
    uuid
);


ALTER TABLE public.profile_connections
    OWNER TO wellbeeing;

--
-- Name: profile_diet_calculation; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.profile_diet_calculation
(
    id
    uuid
    NOT
    NULL,
    basic_metabolism
    double
    precision,
    bmi
    double
    precision,
    bmi_result_type
    character
    varying
(
    255
),
    suggested_calories double precision,
    suggested_carbohydrates double precision,
    suggested_fats double precision,
    suggested_proteins double precision
    );


ALTER TABLE public.profile_diet_calculation
    OWNER TO wellbeeing;

--
-- Name: report; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.report
(
    id
    uuid
    NOT
    NULL,
    derived_caffeine
    double
    precision,
    derived_calories
    double
    precision,
    derived_carbohydrates
    double
    precision,
    derived_cholesterol
    double
    precision,
    derived_fats
    double
    precision,
    derived_fiber
    double
    precision,
    derived_proteins
    double
    precision,
    derived_salt
    double
    precision,
    derived_saturated_fats
    double
    precision,
    derived_sugar
    double
    precision,
    report_date
    date,
    owner_profile_id
    uuid
);


ALTER TABLE public.report
    OWNER TO wellbeeing;

--
-- Name: report_dish_detail; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.report_dish_detail
(
    id
    uuid
    NOT
    NULL,
    consuming_time
    timestamp
    without
    time
    zone,
    derived_caffeine
    double
    precision,
    derived_calories
    double
    precision,
    derived_carbohydrates
    double
    precision,
    derived_cholesterol
    double
    precision,
    derived_fats
    double
    precision,
    derived_fiber
    double
    precision,
    derived_proteins
    double
    precision,
    derived_salt
    double
    precision,
    derived_saturated_fats
    double
    precision,
    derived_sugar
    double
    precision,
    portions
    double
    precision,
    dish_id
    uuid,
    report_id
    uuid
);


ALTER TABLE public.report_dish_detail
    OWNER TO wellbeeing;

--
-- Name: report_exercise; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.report_exercise
(
    id
    bigint
    NOT
    NULL,
    exercising_time
    timestamp
    without
    time
    zone,
    seconds
    bigint,
    exercise_id
    bigint,
    report_id
    uuid
);


ALTER TABLE public.report_exercise
    OWNER TO wellbeeing;

--
-- Name: report_product_detail; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.report_product_detail
(
    id
    uuid
    NOT
    NULL,
    amount_of_product
    double
    precision,
    measure_type
    character
    varying
(
    255
),
    consuming_time timestamp without time zone,
    derived_caffeine double precision,
    derived_calories double precision,
    derived_carbohydrates double precision,
    derived_cholesterol double precision,
    derived_fats double precision,
    derived_fiber double precision,
    derived_proteins double precision,
    derived_salt double precision,
    derived_saturated_fats double precision,
    derived_sugar double precision,
    product_id uuid,
    report_id uuid
    );


ALTER TABLE public.report_product_detail
    OWNER TO wellbeeing;

--
-- Name: report_training; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.report_training
(
    id
    bigint
    NOT
    NULL,
    seconds
    integer,
    training_time
    timestamp
    without
    time
    zone,
    report_id
    uuid,
    training_id
    bigint
);


ALTER TABLE public.report_training
    OWNER TO wellbeeing;

--
-- Name: role_request; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.role_request
(
    role_req_id
    bigint
    NOT
    NULL,
    added_date
    timestamp
    without
    time
    zone,
    comment
    character
    varying
(
    255
),
    document_img_path character varying
(
    255
),
    role character varying
(
    255
),
    status character varying
(
    255
),
    submitter_id uuid,
    specialization_id uuid
    );


ALTER TABLE public.role_request
    OWNER TO wellbeeing;

--
-- Name: role_request_role_req_id_seq; Type: SEQUENCE; Schema: public; Owner: wellbeeing
--

CREATE SEQUENCE IF NOT EXISTS public.role_request_role_req_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER TABLE public.role_request_role_req_id_seq
    OWNER TO wellbeeing;

--
-- Name: role_request_role_req_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wellbeeing
--

ALTER SEQUENCE public.role_request_role_req_id_seq OWNED BY public.role_request.role_req_id;


--
-- Name: settings; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.settings
(
    def_diet_priv
    integer
    NOT
    NULL,
    def_post_priv
    integer
    NOT
    NULL,
    def_train_priv
    integer
    NOT
    NULL,
    follow_list_priv
    integer
    NOT
    NULL,
    friends_list_priv
    integer
    NOT
    NULL,
    profile_profile_user_id
    uuid
    NOT
    NULL
);


ALTER TABLE public.settings
    OWNER TO wellbeeing;

--
-- Name: sport_label; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.sport_label
(
    sport_label_id
    bigint
    NOT
    NULL,
    name
    character
    varying
(
    255
)
    );


ALTER TABLE public.sport_label
    OWNER TO wellbeeing;

--
-- Name: sport_label_ailment; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.sport_label_ailment
(
    id
    bigint
    NOT
    NULL,
    impact_weight
    double
    precision,
    is_positive
    boolean,
    ailment_id
    uuid
    NOT
    NULL,
    sport_label_id
    bigint
    NOT
    NULL
);


ALTER TABLE public.sport_label_ailment
    OWNER TO wellbeeing;

--
-- Name: sport_label_ailment_id_seq; Type: SEQUENCE; Schema: public; Owner: wellbeeing
--

CREATE SEQUENCE IF NOT EXISTS public.sport_label_ailment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER TABLE public.sport_label_ailment_id_seq
    OWNER TO wellbeeing;

--
-- Name: sport_label_ailment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wellbeeing
--

ALTER SEQUENCE public.sport_label_ailment_id_seq OWNED BY public.sport_label_ailment.id;


--
-- Name: sport_label_sport_label_id_seq; Type: SEQUENCE; Schema: public; Owner: wellbeeing
--

CREATE SEQUENCE IF NOT EXISTS public.sport_label_sport_label_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER TABLE public.sport_label_sport_label_id_seq
    OWNER TO wellbeeing;

--
-- Name: sport_label_sport_label_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wellbeeing
--

ALTER SEQUENCE public.sport_label_sport_label_id_seq OWNED BY public.sport_label.sport_label_id;


--
-- Name: sport_report; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.sport_report
(
    id
    uuid
    NOT
    NULL,
    report_date
    date,
    owner_profile_id
    uuid
);


ALTER TABLE public.sport_report
    OWNER TO wellbeeing;

--
-- Name: trainer_profile; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.trainer_profile
(
    user_id uuid NOT NULL
);


ALTER TABLE public.trainer_profile
    OWNER TO wellbeeing;

--
-- Name: training; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.training
(
    training_id
    bigint
    NOT
    NULL,
    description
    character
    varying
(
    255
),
    instruction character varying
(
    255
),
    is_private boolean,
    name character varying
(
    255
) NOT NULL,
    difficulty character varying
(
    255
) NOT NULL,
    creator uuid,
    is_deleted boolean
    );


ALTER TABLE public.training
    OWNER TO wellbeeing;

--
-- Name: training_plan; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.training_plan
(
    training_plan_id
    bigint
    NOT
    NULL,
    beginning_date
    timestamp
    without
    time
    zone,
    details
    character
    varying
(
    255
),
    status character varying
(
    255
),
    creator uuid,
    owner uuid,
    request bigint
    );


ALTER TABLE public.training_plan
    OWNER TO wellbeeing;

--
-- Name: training_plan_request; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.training_plan_request
(
    request_id
    bigint
    NOT
    NULL,
    message
    character
    varying
(
    255
),
    request_date timestamp without time zone,
    request_status character varying
(
    255
),
    submitter_profile_user_id uuid,
    trainer_user_id uuid,
    beginning_date timestamp
                           without time zone
    );


ALTER TABLE public.training_plan_request
    OWNER TO wellbeeing;

--
-- Name: training_plan_training_plan_id_seq; Type: SEQUENCE; Schema: public; Owner: wellbeeing
--

CREATE SEQUENCE IF NOT EXISTS public.training_plan_training_plan_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER TABLE public.training_plan_training_plan_id_seq
    OWNER TO wellbeeing;

--
-- Name: training_plan_training_plan_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wellbeeing
--

ALTER SEQUENCE public.training_plan_training_plan_id_seq OWNED BY public.training_plan.training_plan_id;


--
-- Name: training_position; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.training_position
(
    training_position_id
    bigint
    NOT
    NULL,
    time_of_day
    character
    varying
(
    255
),
    training_date timestamp without time zone,
    status character varying
(
    255
),
    training_id bigint NOT NULL,
    training_plan_id bigint NOT NULL
    );


ALTER TABLE public.training_position
    OWNER TO wellbeeing;

--
-- Name: training_position_training_position_id_seq; Type: SEQUENCE; Schema: public; Owner: wellbeeing
--

CREATE SEQUENCE IF NOT EXISTS public.training_position_training_position_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER TABLE public.training_position_training_position_id_seq
    OWNER TO wellbeeing;

--
-- Name: training_position_training_position_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wellbeeing
--

ALTER SEQUENCE public.training_position_training_position_id_seq OWNED BY public.training_position.training_position_id;


--
-- Name: training_training_id_seq; Type: SEQUENCE; Schema: public; Owner: wellbeeing
--

CREATE SEQUENCE IF NOT EXISTS public.training_training_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER TABLE public.training_training_id_seq
    OWNER TO wellbeeing;

--
-- Name: training_training_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wellbeeing
--

ALTER SEQUENCE public.training_training_id_seq OWNED BY public.training.training_id;


--
-- Name: user_roles; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.user_roles
(
    id
    uuid
    NOT
    NULL,
    role_id
    uuid
    NOT
    NULL
);


ALTER TABLE public.user_roles
    OWNER TO wellbeeing;

--
-- Name: wb_role; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.wb_role
(
    role_id
    uuid
    NOT
    NULL,
    authority
    character
    varying
(
    255
) NOT NULL
    );


ALTER TABLE public.wb_role
    OWNER TO wellbeeing;

--
-- Name: wb_user; Type: TABLE; Schema: public; Owner: wellbeeing
--

CREATE TABLE IF NOT EXISTS public.wb_user
(
    id
    uuid
    NOT
    NULL,
    email
    character
    varying
(
    255
) NOT NULL,
    password character varying
(
    255
) NOT NULL,
    last_request_time timestamp without time zone
    );


ALTER TABLE public.wb_user
    OWNER TO wellbeeing;

--
-- Name: activity_goal activity_goal_id; Type: DEFAULT; Schema: public; Owner: wellbeeing
--

ALTER TABLE ONLY public.activity_goal
ALTER
COLUMN activity_goal_id SET DEFAULT nextval('public.activity_goal_activity_goal_id_seq'::regclass);


--
-- Name: comment comment_id; Type: DEFAULT; Schema: public; Owner: wellbeeing
--

ALTER TABLE ONLY public.comment
ALTER
COLUMN comment_id SET DEFAULT nextval('public.comment_comment_id_seq'::regclass);


--
-- Name: exercise exercise_id; Type: DEFAULT; Schema: public; Owner: wellbeeing
--

ALTER TABLE ONLY public.exercise
ALTER
COLUMN exercise_id SET DEFAULT nextval('public.exercise_exercise_id_seq'::regclass);


--
-- Name: exercise_in_training id; Type: DEFAULT; Schema: public; Owner: wellbeeing
--

ALTER TABLE ONLY public.exercise_in_training
ALTER
COLUMN id SET DEFAULT nextval('public.exercise_in_training_id_seq'::regclass);


--
-- Name: follow follow_id; Type: DEFAULT; Schema: public; Owner: wellbeeing
--

ALTER TABLE ONLY public.follow
ALTER
COLUMN follow_id SET DEFAULT nextval('public.follow_follow_id_seq'::regclass);


--
-- Name: likes like_id; Type: DEFAULT; Schema: public; Owner: wellbeeing
--

ALTER TABLE ONLY public.likes
ALTER
COLUMN like_id SET DEFAULT nextval('public.likes_like_id_seq'::regclass);


--
-- Name: opinion opinion_id; Type: DEFAULT; Schema: public; Owner: wellbeeing
--

ALTER TABLE ONLY public.opinion
ALTER
COLUMN opinion_id SET DEFAULT nextval('public.opinion_opinion_id_seq'::regclass);


--
-- Name: post post_id; Type: DEFAULT; Schema: public; Owner: wellbeeing
--

ALTER TABLE ONLY public.post
ALTER
COLUMN post_id SET DEFAULT nextval('public.post_post_id_seq'::regclass);


--
-- Name: role_request role_req_id; Type: DEFAULT; Schema: public; Owner: wellbeeing
--

ALTER TABLE ONLY public.role_request
ALTER
COLUMN role_req_id SET DEFAULT nextval('public.role_request_role_req_id_seq'::regclass);


--
-- Name: sport_label sport_label_id; Type: DEFAULT; Schema: public; Owner: wellbeeing
--

ALTER TABLE ONLY public.sport_label
ALTER
COLUMN sport_label_id SET DEFAULT nextval('public.sport_label_sport_label_id_seq'::regclass);


--
-- Name: sport_label_ailment id; Type: DEFAULT; Schema: public; Owner: wellbeeing
--

ALTER TABLE ONLY public.sport_label_ailment
ALTER
COLUMN id SET DEFAULT nextval('public.sport_label_ailment_id_seq'::regclass);


--
-- Name: training training_id; Type: DEFAULT; Schema: public; Owner: wellbeeing
--

ALTER TABLE ONLY public.training
ALTER
COLUMN training_id SET DEFAULT nextval('public.training_training_id_seq'::regclass);


--
-- Name: training_plan training_plan_id; Type: DEFAULT; Schema: public; Owner: wellbeeing
--

ALTER TABLE ONLY public.training_plan
ALTER
COLUMN training_plan_id SET DEFAULT nextval('public.training_plan_training_plan_id_seq'::regclass);


--
-- Name: training_position training_position_id; Type: DEFAULT; Schema: public; Owner: wellbeeing
--

ALTER TABLE ONLY public.training_position
ALTER
COLUMN training_position_id SET DEFAULT nextval('public.training_position_training_position_id_seq'::regclass);


