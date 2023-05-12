package com.ahmadrenhoran.pregnantz.ui.feature.home

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadrenhoran.pregnantz.R
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.core.PregnancyUtils
import com.ahmadrenhoran.pregnantz.domain.model.BabyData
import com.ahmadrenhoran.pregnantz.domain.model.BabySize
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.model.User
import com.ahmadrenhoran.pregnantz.domain.usecase.home.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val useCase: HomeUseCase): ViewModel() {


    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    val dueDate get() = LocalDate.parse(_uiState.value.user.dueDate)
    val currentWeek get() = PregnancyUtils.getCurrentWeek(dueDate = dueDate)
    val currentDays get() = PregnancyUtils.getCurrentDays(dueDate = dueDate)
    val trimester get() = PregnancyUtils.getTrimester(currentWeek)

    val babyData: BabyData get() {
        @DrawableRes
        var babyImage = R.drawable.baby_week1_day1
        var babySize = BabySize(R.drawable.baby_fruit1_3_dot, "what baby?", 0f, 0f)
        var babyDesc = ""
        when (currentWeek) {
            1 -> {
                babyImage = R.drawable.baby_week1_day1
                babySize = BabySize(R.drawable.baby_fruit1_3_dot, "what baby?", 0f, 0f)
                babyDesc = "Conception, referred to as fertilization as well, generally takes place around two weeks after the commencement of your most recent menstrual period (also known as LMP). It involves the union of a man's sperm with a woman's egg, occurring within one of the fallopian tubes, the tubes connecting the ovaries and the uterus (womb). Pinpointing the exact day of conception may be difficult, which is why healthcare providers utilize the LMP to estimate the duration of pregnancy."
            }
            2 -> {
                babyImage = R.drawable.baby_week1_day6_8
                babySize = BabySize(R.drawable.baby_fruit1_3_dot, "Still no baby", 0f, 0f)
                babyDesc = "Conception, referred to as fertilization as well, generally takes place around two weeks after the commencement of your most recent menstrual period (also known as LMP). It involves the union of a man's sperm with a woman's egg, occurring within one of the fallopian tubes, the tubes connecting the ovaries and the uterus (womb). Pinpointing the exact day of conception may be difficult, which is why healthcare providers utilize the LMP to estimate the duration of pregnancy."
            }
            3 -> {
                babyImage = R.drawable.baby_week3
                babySize = BabySize(R.drawable.baby_fruit1_3_dot, "wait in week 4", 0f, 0f)
                babyDesc = "Following fertilization, the embryo migrates through the fallopian tubes and eventually implants itself into the uterine lining. Once implanted, it initiates its growth process while the placenta starts to develop. The placenta, situated in the uterus, acts as a vital link between the mother and the growing fetus, providing essential nutrients and oxygen through the umbilical cord."
            }
            4 -> {
                babyImage = R.drawable.baby_week3
                babySize = BabySize(R.drawable.baby_fruit4, "Your baby is the size of a poppy seed", 0f, 0f)
                babyDesc = "Following fertilization, the embryo migrates through the fallopian tubes and eventually implants itself into the uterine lining. Once implanted, it initiates its growth process while the placenta starts to develop. The placenta, situated in the uterus, acts as a vital link between the mother and the growing fetus, providing essential nutrients and oxygen through the umbilical cord."
            }
            5 -> {
                babyImage = R.drawable.baby_month1
                babySize = BabySize(R.drawable.baby_fruit5, "Your baby is about the size of a sesame seed", 0f, 0f)
                babyDesc = "The formation of your baby's neural tube begins, which eventually develops into their brain, spinal cord, and backbone. Small buds emerge, which will later develop into your baby's arms and legs. The development of your baby's heart and lungs is underway, and their heart begins to beat."
            }
            6 -> {
                babyImage = R.drawable.baby_month1
                babySize = BabySize(R.drawable.baby_fruit6_lentil, "Your baby is about the size of a lentil", 0f, 0f)
                babyDesc = "Your baby's heart is beating at approximately 105 times per minute. The formation of her nose, mouth, fingers, toes, and ears is occurring, and they are starting to take shape."
            }
            7 -> {
                babyImage = R.drawable.baby_month1
                babySize = BabySize(R.drawable.baby_fruit7_blueberry, "Your baby is about the size of a blueberry", 0f, 0f)
                babyDesc = "Your baby's bones start to form but are still soft. They harden as you get farther along in your pregnancy. She now has eyelids, but they stay shut. Your baby's genitals begin to form."
            }
            8 -> {
                babyImage = R.drawable.baby_month1
                babySize = BabySize(R.drawable.baby_fruit8_kidneybean, "Your baby is about the size of a kidney bean", 1.57f, 20f)
                babyDesc = "All of your baby's major organs and body systems are developing. The placenta is working."
            }
            9 -> {
                babyImage = R.drawable.baby_month2
                babySize = BabySize(R.drawable.baby_fruit9_grape, "Your baby is about the size of a grape", 2.30f, 27f)
                babyDesc = "Tiny buds appear that become your baby's teeth. Your baby is close to ½ an inch long now."
            }
            10 -> {
                babyImage = R.drawable.baby_month2
                babySize = BabySize(R.drawable.baby_fruit10_kumquat, "Your baby is about the size of a kumquat", 3.1f, 35f)
                babyDesc = "Continued development of fingers and toes is taking place, along with the growth of your baby's nails. During your prenatal care checkup, you may have the opportunity to hear your baby's heartbeat."
            }
            11 -> {
                babyImage = R.drawable.baby_month2
                babySize = BabySize(R.drawable.baby_fruit11_fig, "Your baby is about the size of a fig", 4.1f, 45f)
                babyDesc = "Your baby's bones begin to get hard. Her skin is still thin and see-through but gets less see-through over time. Her head makes up about half of her size."
            }
            12 -> {
                babyImage = R.drawable.baby_month2
                babySize = BabySize(R.drawable.baby_fruit12_lime, "Your baby is about the size of a lime", 5.4f, 58f)
                babyDesc = "Your baby's hands exhibit more rapid development compared to her feet. Although she is active and moving, you may not yet perceive her movements. At this stage, she measures approximately 2 inches in length and weighs around ½ an ounce."
            }
            13 -> {
                babyImage = R.drawable.baby_month3
                babySize = BabySize(R.drawable.baby_fruit13_peapod, "Your baby is about the size of a peapod", 6.7f, 73f)
                babyDesc ="You have entered the second trimester of your pregnancy! Your baby is undergoing rapid growth. Her organs have already formed and are continuing to develop. During an ultrasound, you may observe movements that resemble breathing and swallowing in your baby."
            }
            14 -> {
                babyImage = R.drawable.baby_month3
                babySize = BabySize(R.drawable.baby_fruit14_lemon, "Your baby is about the size of a lemon", 14.7f, 93f)
                babyDesc = "Your baby starts to move her eyes. Her nose and taste buds are developing. Her skin starts to thicken, and hair follicles under her skin begin to grow. Your baby opens and closes her hands and brings them to her mouth."
            }
            15 -> {
                babyImage = R.drawable.baby_month3
                babySize = BabySize(R.drawable.baby_fruit15_apple, "Your baby is about the size of an apple", 16.7f, 117f)
                babyDesc = "Your baby is very active! She flips and rolls around inside you. You may begin to feel her move. Her bones are growing strong, and you may be able to see them during an ultrasound. Your baby's kidneys make urine and her heart is pumping blood."
            }
            16 -> {
                babyImage = R.drawable.baby_month3
                babySize = BabySize(R.drawable.baby_fruit16_avocado, "Your baby is about the size of an avocado", 18.6f, 146f)
                babyDesc = "Your baby's eyelids, upper lip, and ears have reached a significant stage of development. She has also developed the ability to hear, so feel free to engage in conversations or sing to her as often as you wish. At this point, your baby measures approximately 5 inches in length and weighs around 5 ounces."
            }
            17 -> {
                babyImage = R.drawable.baby_month4
                babySize = BabySize(R.drawable.baby_fruit17_turnip, "Your baby is about the size of a turnip", 20.4f, 181f)
                babyDesc = "Your baby starts to add fat to her body! Fat gives your baby energy and helps her stay warm after she's born. Vernix appears on your baby's skin. This is a waxy or greasy coating that's waterproof. It protects your baby's skin in the womb."
            }
            18 -> {
                babyImage = R.drawable.baby_month4
                babySize = BabySize(R.drawable.baby_fruit18_bellpepper, "Your baby is about the size of a bell pepper", 22.2f, 223f)
                babyDesc = "You may have your first ultrasound this week—it's the first time you get to \"see\" your baby! You may be able to tell if your baby's a boy or girl, so be sure to tell your provider if you don’t want to know. Your baby goes to sleep and wakes up throughout the day. Loud noises and your movements can wake her. Her skin has lanugo. This is soft, fine hair that helps keep her warm in the womb. rephrase these words"
            }
            19 -> {
                babyImage = R.drawable.baby_month4
                babySize = BabySize(R.drawable.baby_fruit19_heirloomtomato, "Your baby is about the size of an heirloom tomato", 24.0f, 273f)
                babyDesc = "Your baby's kicks and movements are getting stronger! If you think you felt them before, you really can feel her move now. She learns how to suck, which she needs for feeding after she's born. She may even suck her thumb in the womb."
            }
            20 -> {
                babyImage = R.drawable.baby_month4
                babySize = BabySize(R.drawable.baby_fruit20_banana, "Your baby is about the length of a banana", 25.7f, 331f)
                babyDesc = "Your baby's nails grow toward the ends of her fingers. Your baby is about 10 inches long and weighs about 1 pound."
            }
            21 -> {
                babyImage = R.drawable.baby_month5
                babySize = BabySize(R.drawable.baby_fruit21_carrot, "Your baby is about as long as a carrot", 27.4f, 399f)
                babyDesc = "Your baby's fingers and toes are fully formed, including her finger prints and toe prints. Your baby can swallow now and from time to time, she may even hiccup! You may feel these as regular, jerky movements."
            }
            22 -> {
                babyImage = R.drawable.baby_month5
                babySize = BabySize(R.drawable.baby_fruit22_spaghettisquash, "Your baby is about the size of a spaghetti squash", 29f, 478f)
                babyDesc = "Your baby's eyelids are still shut, but her eyes are moving behind them. Her tear ducts start to develop, and her eyebrows may begin to appear. Your baby may move suddenly when she hears loud sounds."
            }
            23 -> {
                babyImage = R.drawable.baby_month5
                babySize = BabySize(R.drawable.baby_fruit23_largemango, "Your baby is about the size of a large mango", 30.6f, 568f)
                babyDesc = "Your baby may recognize sounds, like your voice. If you talk to your baby, you may feel her move!"
            }
            24 -> {
                babyImage = R.drawable.baby_month5
                babySize = BabySize(R.drawable.baby_fruit24_corn, "Your baby is about as long as an ear of corn", 32.2f, 670f)
                babyDesc = "Your baby's muscles continue to grow. She may start to have hair on her head. Her lungs are fully formed but she's not ready to breathe outside the womb yet. She's about 12 inches long and may weigh a little more than 1 pound."
            }
            25 -> {
                babyImage = R.drawable.baby_month6
                babySize = BabySize(R.drawable.baby_fruit25_rutabaga, "Your baby is about the size of a rutabaga", 33.7f, 785f)
                babyDesc = "Her nervous system is developing quickly. The nervous system is the brain, spinal cord and nerves. It helps your baby move, think and feel. Your baby adds more fat to her body, which makes her skin look smooth and less wrinkly."
            }
            26 -> {
                babyImage = R.drawable.baby_month6
                babySize = BabySize(R.drawable.baby_fruit26_scallion, "Your baby is about the length of a scallion", 35.1f, 913f)
                babyDesc = "Your baby's body is making melanin, a substance that gives her skin color and protects her skin from the sun after birth. Her lungs start to make surfactant. This substance helps your baby's lungs get ready to breathe."
            }
            27 -> {
                babyImage = R.drawable.baby_month6
                babySize = BabySize(R.drawable.baby_fruit27_cauliflower, "Your baby is about the size of a head of cauliflower", 36.6f, 1055f)
                babyDesc = "Your baby is doing lots of kicking and stretching. Her lungs and nervous system continue to develop."
            }
            28 -> {
                babyImage = R.drawable.baby_month6
                babySize = BabySize(R.drawable.baby_fruit28_eggplant, "Your baby is about the size of a large eggplant", 37.6f, 1210f)
                babyDesc = "You have entered the third trimester of pregnancy! Your baby has eyelashes and she can open and close her eyes. Your baby is about 14 inches long and weighs about 2½ pounds."
            }
            29 -> {
                babyImage = R.drawable.baby_month7
                babySize = BabySize(R.drawable.baby_fruit29_butternutsquash, "Your baby is about the size of a butternut squash", 39.3f, 1379f)
                babyDesc = "Your baby starts to put on weight fast! In the last 2½ months of pregnancy, your baby gains about half of her birthweight. Be sure to eat healthy foods so your baby has the nutrients she needs to grow."
            }
            30 -> {
                babyImage = R.drawable.baby_month7
                babySize = BabySize(R.drawable.baby_fruit30_cabbage, "Your baby is about the size of a large cabbage", 40.5f, 1559f)
                babyDesc = "Your baby begins to lose the lanugo, the soft fine hair that covers her body. She also may have a good amount of hair on her head."
            }
            31 -> {
                babyImage = R.drawable.baby_month7
                babySize = BabySize(R.drawable.baby_fruit31_coconut, "Your baby is about the size of a coconut", 41.8f, 1751f)
                babyDesc = "Your baby's brain grows and develops quickly. Her brain can now help control her body heat."
            }
            32 -> {
                babyImage = R.drawable.baby_month7
                babySize = BabySize(R.drawable.baby_fruit32_jicama, "Your baby is about the size of a jicama", 43f, 1953f)
                babyDesc = "As your baby adds fat to her body, her skin is no longer see-through. Your baby is about 18 inches long and may weigh about 5 pounds."
            }
            33 -> {
                babyImage = R.drawable.baby_month8
                babySize = BabySize(R.drawable.baby_fruit33_pineapple, "Your baby is about the size of a pineapple", 44.1f, 2162f)
                babyDesc = "Your baby's still gaining weight and growing. As you get closer to your due date, she gains about ½ pound per week."
            }
            34 -> {
                babyImage = R.drawable.baby_month8
                babySize = BabySize(R.drawable.baby_fruit34_cantaloupe, "Your baby is about the size of cantaloupe", 45.3f, 2377f)
                babyDesc = "The vernix, the waxy, greasy coating that protects your baby's skin in the womb, starts to get thicker. Most babies move into a head-down position to get ready for labor and birth. It may happen this week or in the next few weeks."
            }
            35 -> {
                babyImage = R.drawable.baby_month8
                babySize = BabySize(R.drawable.baby_fruit35_honeydewlemon, "Your baby is about the size of a honeydew melon", 46.3f, 2595f)
                babyDesc = "Your baby's brain and lungs are still developing. A baby's brain at 35 weeks weighs only two-thirds of what it will weigh at 39 to 40 weeks. If your pregnancy is healthy, wait for labor to begin on its own. If you're planning to schedule a c-section or labor induction before 39 weeks, it should only be for medical reasons."
            }
            36 -> {
                babyImage = R.drawable.baby_month8
                babySize = BabySize(R.drawable.baby_fruit36_lettuce, "Your baby is about as long as a head of romaine lettuce", 47.3f, 2813f)
                babyDesc = "It's starting to get crowded in the womb! While your baby doesn't have room to do many flips or rolls, you still feel her kick and stretch. If you notice a change in how often your baby moves, call your health care provider. Your baby weighs about 6 to 7 pounds."
            }
            37 -> {
                babyImage = R.drawable.baby_month9
                babySize = BabySize(R.drawable.baby_fruit37_swisschard, "Your baby is about the length of a bunch of Swiss chard", 48.3f, 3028f)
                babyDesc = "Important organs, like your baby's brain, lungs and liver, are still developing. Your baby's still gaining weight. If your pregnancy is healthy, it's best to stay pregnant for at least 39 weeks. Births scheduled before 39 weeks should be for medical reasons only."
            }
            38 -> {
                babyImage = R.drawable.baby_month9
                babySize = BabySize(R.drawable.baby_fruit38_leek, "Your baby is about the length of a leek", 49.3f, 3236f)
                babyDesc = "Your baby's brain is still developing. Her liver and lungs are still growing. Your baby's size may make you feel uncomfortable. Hang in there! If your pregnancy is healthy, wait for labor to begin on its own."
            }
            39 -> {
                babyImage = R.drawable.baby_month9
                babySize = BabySize(R.drawable.baby_fruit39_miniwatermelon, "Your baby is about the size of a mini-watermelon", 50.1f, 3435f)
                babyDesc = "You and your baby have made it to 39 weeks! This is great! Your baby is full term. She'll let you know when she's ready to be born. Call your provider when you think you're in labor."
            }
            40 -> {
                babyImage = R.drawable.baby_month9
                babySize = BabySize(R.drawable.baby_fruit40_smallpumpkin, "Your baby is about the size of a small pumpkin", 51f, 3619f)
                babyDesc = "Congratulations on 40 weeks! Your baby is ready to be born. Your baby's had time to fully develop and is ready to meet you face to face. Call your provider when you think you're in labor. Your baby is about 18 to 20 inches long and weighs about 6 to 9 pounds."
            }
        }
        return BabyData(babyImage = babyImage, babySize = babySize, babyDescription = babyDesc)
    }

    val whatToDo: String get() {

        return when (currentWeek) {
            in Constants.FIRST_TRIMESTER -> {
                "- Take prenatal vitamins.\n" +
                        "- Exercise regularly.\n" +
                        "- Work out your pelvic floor by doing Kegel exercises.\n" +
                        "- Eat a diet high in fruits, vegetables, low-fat forms of protein, and fiber.\n" +
                        "- Drink lots of water.\n" +
                        "- Eat enough calories (about 300 calories more than normal)."
            }
            in Constants.SECOND_TRIMESTER -> {
                "- Continue to take prenatal vitamins.\n" +
                        "- Exercise regularly.\n" +
                        "- Work out your pelvic floor by doing Kegel exercises.\n" +
                        "- Eat a diet high in fruits, vegetables, low-fat forms of protein, and fiber.\n" +
                        "- Drink lots of water.\n" +
                        "- Eat enough calories (about 300 calories more than normal).\n" +
                        "- Keep your teeth and gums healthy. Poor dental hygiene is linked to premature labor."
            }
            in Constants.THIRD_TRIMESTER -> {
                "- Continue to take prenatal vitamins.\n" +
                        "- Stay active unless you’re experiencing swelling or pain.\n" +
                        "- Work out your pelvic floor by doing Kegel exercises.\n" +
                        "- Eat a diet high in fruits, vegetables, low-fat forms of protein, and fiber.\n" +
                        "- Drink lots of water.\n" +
                        "- Eat enough calories (about 300 more calories than normal per day).\n" +
                        "- Stay active with walking.\n" +
                        "- Keep your teeth and gums healthy. Poor dental hygiene is linked to premature labor.\n" +
                        "- Get plenty of rest and sleep."
            }
            else -> { "" }
        }
    }

    val whatToAvoid: String get() {

        return when (currentWeek) {
            in Constants.FIRST_TRIMESTER -> {
                "- Strenuous exercise or strength training that could cause an injury to your stomach\n" +
                        "- Alcohol\n" +
                        "- Caffeine (no more than one cup of coffee or tea per day)\n" +
                        "- Smoking\n" +
                        "- Illegal drugs\n" +
                        "- Raw fish or smoked seafood (no sushi)\n" +
                        "- Shark, swordfish, mackerel, or white snapper fish (they have high levels of mercury)\n" +
                        "- Raw sprouts\n" +
                        "- Cat litter, which can carry a parasitic disease called toxoplasmosis\n" +
                        "- Unpasteurized milk or other dairy products\n" +
                        "- Deli meats or hot dogs"
            }
            in Constants.SECOND_TRIMESTER -> {
                "- Strenuous exercise or strength training that could cause an injury to your stomach\n" +
                        "- Alcohol\n" +
                        "- Caffeine (no more than one cup of coffee or tea per day)\n" +
                        "- Smoking\n" +
                        "- Illegal drugs\n" +
                        "- Raw fish or smoked seafood\n" +
                        "- Shark, swordfish, mackerel, or white snapper fish (they have high levels of mercury)\n" +
                        "- Raw sprouts\n" +
                        "- Cat litter, which can carry a parasite that causes toxoplasmosis\n" +
                        "- Unpasteurized milk or other dairy products\n" +
                        "- Deli meats or hot dogs\n" +
                        "- The following prescription drugs: isotretinoin (Accutane) for acne, acitretin (Soriatane) for psoriasis, thalidomide (Thalomid), and ACE inhibitors for high blood pressure"
            }
            in Constants.THIRD_TRIMESTER -> {

                "- Strenuous exercise or strength training that could cause an injury to your stomach\n" +
                        "- Alcohol\n" +
                        "- Caffeine (no more than one cup of coffee or tea per day)\n" +
                        "- Smoking\n" +
                        "- Illegal drugs\n" +
                        "- Raw fish or smoked seafood\n" +
                        "- Shark, swordfish, mackerel, or white snapper fish (they have high levels of mercury)\n" +
                        "- Raw sprouts\n" +
                        "- Cat litter, which can carry a parasite that causes toxoplasmosis\n" +
                        "- Unpasteurized milk or other dairy products\n" +
                        "- Deli meats or hot dogs\n" +
                        "- The following prescription drugs: isotretinoin (Accutane) for acne, acitretin (Soriatane) for psoriasis, thalidomide (Thalomid), and ACE inhibitors for high blood pressure\n" +
                        "- Long car trips and airplane flights, if possible (after 34 weeks, airlines may not let you board the plane because of the possibility of an unexpected delivery on the plane)"
            }
            else -> {
                ""
            }
        }
    }

    var getUserDataResponse by mutableStateOf<Response<User>>(
        Response.Success(User())
    )
        private set

    init {
        getUserData()
    }

    fun getUserData() = viewModelScope.launch {
        getUserDataResponse = Response.Loading
        getUserDataResponse = useCase.getUserData.invoke()
    }

    fun setUser(user: User) {
        _uiState.update { it.copy(user = user) }
    }

}