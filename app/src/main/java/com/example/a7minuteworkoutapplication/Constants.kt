package com.example.a7minuteworkoutapplication

class Constants {
    //same thing as Static,so can be used through out the project without creating object of Constant class
    companion object{
        fun defaultExerciseList(): ArrayList<ExerciseModel>{
            var exerciseList = ArrayList<ExerciseModel>()

            val jumpingJacks = ExerciseModel(1,
                "Jumping Jacks",
                R.drawable.ic_jumping_jacks,
                false,
                false)

            exerciseList.add(jumpingJacks)

            val wallSit = ExerciseModel(2,
                "Wall Sit",
                R.drawable.ic_wall_sit,
                false,
                false)

            exerciseList.add(wallSit)

            val tricepsDips = ExerciseModel(3,
                "Triceps Dips",
                R.drawable.ic_triceps_dip_on_chair,
                false,
                false)

            exerciseList.add(tricepsDips)

            val stepUp = ExerciseModel(4,
                "Step Up",
                R.drawable.ic_step_up_onto_chair,
                false,
                false)

            exerciseList.add(stepUp)

            val squat = ExerciseModel(5,
                "Squat",
                R.drawable.ic_squat,
                false,
                false)

            exerciseList.add(squat)

            val sidePlank = ExerciseModel(6,
                "Side Plank",
                R.drawable.ic_side_plank,
                false,
                false)

            exerciseList.add(sidePlank)

            val pushUpRotation = ExerciseModel(7,
                "Push Up And Rotation",
                R.drawable.ic_push_up_and_rotation,
                false,
                false)

            exerciseList.add(pushUpRotation)

            val pushUp = ExerciseModel(8,
                "Push Up",
                R.drawable.ic_push_up,
                false,
                false)

            exerciseList.add(pushUp)

            val plank = ExerciseModel(9,
                "Plank",
                R.drawable.ic_plank,
                false,
                false)

            exerciseList.add(plank)

            val lunge = ExerciseModel(10,
                "Lunge",
                R.drawable.ic_lunge,
                false,
                false)

            exerciseList.add(lunge)

            val highKneeRunning = ExerciseModel(11,
                "highKneeRunning",
                R.drawable.ic_high_knees_running_in_place,
                false,
                false)

            exerciseList.add(highKneeRunning)

            val abdominalCrunch = ExerciseModel(12,
                "Abdominal Crunch",
                R.drawable.ic_abdominal_crunch,
                false,
                false)

            exerciseList.add(abdominalCrunch)

            return exerciseList
        }
    }
}